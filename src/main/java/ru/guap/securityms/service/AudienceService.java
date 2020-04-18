package ru.guap.securityms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guap.securityms.domain.Audience;
import ru.guap.securityms.repos.AudienceRepo;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Service
public class AudienceService {

    @Autowired
    AudienceRepo audienceRepo;

    @Autowired
    Lock lock;


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

    public void endReserveAudience(Long userId, Long audienceId) {
        audienceRepo.updatePrincipalId(null, audienceId);
    }

    public List<Map<String, String>> getAudiences() {
        List<Map<String, String>> model = new ArrayList<>();
        audienceRepo.findAll().forEach(audience -> {
            model.add(new HashMap<>() {{
                put("id", audience.getId().toString());
                put("number", audience.getNumber());
                put("principal_id", String.valueOf(audience.getPrincipalId()));
            }});
        });
        return model;
    }

    public boolean isReserved(Long audienceId) {
        return Objects.nonNull(audienceRepo.findById(audienceId).get().getPrincipalId());
    }

}
