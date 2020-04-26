package ru.guap.securityms.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.guap.securityms.service.ScheduleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("{id}")
    public List<Map<String, String>> getSchedule(@PathVariable Integer id) {
        return scheduleService.getTodayByAudience(id);
    }
}
