package ru.guap.securityms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guap.securityms.domain.Audience;
import ru.guap.securityms.domain.Schedule;
import ru.guap.securityms.repos.AudienceRepo;
import ru.guap.securityms.service.base.IService;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AudienceService implements IService<Audience> {

    @Autowired
    AudienceRepo audienceRepo;

    @Autowired
    ScheduleService scheduleService;

    @Transactional
    public synchronized boolean reserveAudience(Integer userId, Integer audienceId) {
        if (isReserved(audienceId)) {
            return false;
        }
        audienceRepo.updatePrincipalId(userId, audienceId);
        return true;
    }

    @Transactional
    public synchronized boolean endReserveAudience(Integer userId, Integer audienceId) {
        if (checkPrincipal(userId, audienceId)) {
            return false;
        }
        audienceRepo.updatePrincipalId(null, audienceId);
        return true;
    }

    public List<Map<String, String>> getAudiences(Short building, Short floor) {
        return createJson(audienceRepo.findByBuildingAndFloorOrderByNumber(building, floor));
    }

    public boolean isReserved(Integer audienceId) {
        return Objects.nonNull(audienceRepo.findById(audienceId).get().getPrincipalId());
    }

    public boolean checkPrincipal(Integer userId, Integer audienceId) {
        return userId.equals(audienceRepo.findById(audienceId).get().getPrincipalId());
    }

    //TODO Доделать фильтр после тестов

    @Override
    public List<Map<String, String>> createJson(Iterable<Audience> audiences) {
        List<Map<String, String>> model = new ArrayList<>();
        audiences.forEach(
                audience -> {
                    Optional<Schedule> schedule = audience.getSchedule().stream().filter(sched ->
                            sched.getAudienceId().equals(audience.getId())
                                    && sched.getDayOfWeek().equals("MONDAY")
                    ).findFirst();
                    Map<String, String> node = createJsonNode(audience);

                    if (schedule.isPresent()) {
                        node.putAll(scheduleService.createJsonNode(schedule.get()));
                    }

                    model.add(node);
                });
        return model;
    }

    @Override
    public Map<String, String> createJsonNode(Audience audience) {
        return new HashMap<>() {{
            put("id", audience.getId().toString());
            put("number", audience.getNumber());
            put("building", String.valueOf(audience.getBuilding()));
            put("floor", String.valueOf(audience.getFloor()));
            put("principal_id", String.valueOf(audience.getPrincipalId()));
        }};
    }
}
