package ru.guap.securityms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guap.securityms.domain.Schedule;
import ru.guap.securityms.repos.ScheduleRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepo scheduleRepo;

    /**
     * @param dayOfWeek
     * @param audienceNumber
     * @return
     */
    public List<Map<String, String>> getByDayAndAudience(String dayOfWeek, String audienceNumber) {
        return createJson(scheduleRepo.findAllByDayOfWeekAndAudience_numberLikeIgnoreCase(dayOfWeek, audienceNumber));
    }

    /**
     * @param floor
     * @param building
     * @return
     */
    public List<Map<String, String>> getByFloorAndBuilding(Short floor, Short building) {
        return createJson(scheduleRepo.findAllByAudience_buildingAndAudience_floorOrderByAudience_number(floor, building));
    }

    private List<Map<String, String>> createJson(Iterable<Schedule> schedule) {
        List<Map<String, String>> model = new ArrayList<>();
        schedule.forEach(
                scheduleLine -> {
                    model.add(new HashMap<>() {{
                        put("id", String.valueOf(scheduleLine.getId()));
                        put("day", scheduleLine.getDayOfWeek());
                        put("isLow", String.valueOf(scheduleLine.getLowerWeek()));
                        put("isUp", String.valueOf(scheduleLine.getUpperWeek()));
                        put("group", scheduleLine.getGroupNumber());
                        put("professor", scheduleLine.getProfessorName());
                        put("prof_degree", scheduleLine.getProfessorDegree());
                        put("subject", scheduleLine.getSubjectName());
                        put("subject_type", scheduleLine.getSubjectTypeName());
                        put("audience_id", String.valueOf(scheduleLine.getAudienceId()));
                        put("audience", scheduleLine.getAudienceNumber());
                    }});
                }
        );
        return model;
    }
}

