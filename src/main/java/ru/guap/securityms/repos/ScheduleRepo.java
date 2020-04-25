package ru.guap.securityms.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.guap.securityms.domain.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Integer> {

    List<Schedule> findAllByDayOfWeekAndAudience_numberLikeIgnoreCase(String dayOfWeek, String number);

    List<Schedule> findAllByAudience_buildingAndAudience_floorOrderByAudience_number(Short floor, Short building);
}
