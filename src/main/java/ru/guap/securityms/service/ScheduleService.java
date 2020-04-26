package ru.guap.securityms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guap.securityms.domain.Schedule;
import ru.guap.securityms.repos.ScheduleRepo;
import ru.guap.securityms.service.base.IService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService implements IService<Schedule> {

    @Autowired
    ScheduleRepo scheduleRepo;

    /**
     * @param dayOfWeek
     * @param audienceId
     * @return
     */
    public List<Map<String, String>> getTodayByAudience(Integer id) {
        return createJson(
                scheduleRepo.findAllByDayOfWeekAndAudience_id("MONDAY", id)
        );
    }

    /**
     * @param floor
     * @param building
     * @return
     */
    public List<Map<String, String>> getByFloorAndBuilding(Short floor, Short building) {
        return createJson(
                scheduleRepo.findAllByAudience_buildingAndAudience_floorOrderByAudience_number(floor, building)
        );
    }

    @Override
    public List<Map<String, String>> createJson(Iterable<Schedule> schedule) {
        List<Map<String, String>> model = new ArrayList<>();
        schedule.forEach(scheduleLine -> model.add(createJsonNode(scheduleLine)));
        return model;
    }

    @Override
    public Map<String, String> createJsonNode(Schedule schedule) {
        return new HashMap<>() {{
            put("s_id", String.valueOf(schedule.getId()));
            put("day", schedule.getDayOfWeek());
            put("isLow", String.valueOf(schedule.getLowerWeek()));
            put("isUp", String.valueOf(schedule.getUpperWeek()));
            put("group", schedule.getGroupNumber());
            put("professor", schedule.getProfessorName());
            put("prof_degree", schedule.getProfessorDegree());
            put("subject", schedule.getSubjectName());
            put("subject_type", schedule.getSubjectTypeName());
            put("audience_id", String.valueOf(schedule.getAudienceId()));
            put("audience", schedule.getAudienceNumber());
            put("lesson_number", String.valueOf(schedule.getLessonNumber()));
            put("start_time", schedule.getStartTime().format(DateTimeFormatter.ISO_TIME));
            put("end_time", schedule.getEndTime().format(DateTimeFormatter.ISO_TIME));
        }};
    }
}

