package ru.guap.securityms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guap.securityms.domain.Audience;
import ru.guap.securityms.repos.AudienceRepo;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Stream;

@Service
public class AudienceService {

    @Autowired
    AudienceRepo audienceRepo;

    @Autowired
    Lock lock;

    @Transactional
    public void reserveAudience(Long userId, Long audienceId) {
        try {
            lock.tryLock(2, TimeUnit.SECONDS);
            if (!isReserved(audienceId)) {
                audienceRepo.updatePrincipalId(userId, audienceId);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void endReserveAudience(Long userId, Long audienceId) {
        audienceRepo.updatePrincipalId(null, audienceId);
    }

    @Transactional
    public List<Map<String, String>> getAudiences(Short building, Short floor) {
        List<Map<String, String>> model = new ArrayList<>();
        audienceRepo.findByBuildingAndFloorOrderByNumber(building, floor).forEach(audience -> {
            model.add(new HashMap<>() {{
                put("id", audience.getId().toString());
                put("number", audience.getNumber());
                put("principal_id", String.valueOf(audience.getPrincipalId()));
            }});
        });
        return model;
    }

    @Transactional
    public Map<String, String> getAudienceById(Long id) {
        Map<String, String> model = new HashMap<>();
        audienceRepo.findById(id).ifPresent(audience -> {
            model.put("id", audience.getId().toString());
            model.put("number", audience.getNumber());
            model.put("principal_id", String.valueOf(audience.getPrincipalId()));
        });
        return model;
    }

    public boolean isReserved(Long audienceId) {
        return Objects.nonNull(audienceRepo.findById(audienceId).get().getPrincipalId());
    }

}
