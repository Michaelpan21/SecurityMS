package ru.guap.securityms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guap.securityms.repos.AudienceRepo;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AudienceService {

    @Autowired
    AudienceRepo audienceRepo;

    @Transactional
    public synchronized void reserveAudience(Integer userId, Integer audienceId) {
        audienceRepo.updatePrincipalId(userId, audienceId);
    }

    @Transactional
    public synchronized void endReserveAudience(Integer audienceId) {
        audienceRepo.updatePrincipalId(null, audienceId);
    }

    @Transactional
    public List<Map<String, String>> getAudiences(Short building, Short floor) {
        List<Map<String, String>> model = new ArrayList<>();
        audienceRepo.findByBuildingAndFloorOrderByNumber(building, floor).forEach(audience -> {
            model.add(new HashMap<>() {{
                put("id", audience.getId().toString());
                put("number", audience.getNumber());
                put("building", String.valueOf(audience.getBuilding()));
                put("floor", String.valueOf(audience.getFloor()));
                put("principal_id", String.valueOf(audience.getPrincipalId()));
            }});
        });
        return model;
    }

    @Transactional
    public Map<String, String> getAudienceById(Integer id) {
        Map<String, String> model = new HashMap<>();
        audienceRepo.findById(id).ifPresent(audience -> {
            model.put("id", audience.getId().toString());
            model.put("number", audience.getNumber());
            model.put("building", String.valueOf(audience.getBuilding()));
            model.put("floor", String.valueOf(audience.getFloor()));
            model.put("principal_id", String.valueOf(audience.getPrincipalId()));
        });
        return model;
    }

    public boolean isReserved(Integer audienceId) {
        return Objects.nonNull(audienceRepo.findById(audienceId).get().getPrincipalId());
    }

}
