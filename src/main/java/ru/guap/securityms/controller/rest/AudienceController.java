package ru.guap.securityms.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.guap.securityms.domain.User;
import ru.guap.securityms.service.AudienceService;
import ru.guap.securityms.service.utils.AudienceAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("audience")
public class AudienceController {

    @Autowired
    AudienceService audienceService;

    @GetMapping
    public List<Map<String, String>> getAudiences(@RequestParam("building") Short building,
                                                  @RequestParam("floor") Short floor) {
        return audienceService.getAudiences(building, floor);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public Map<String, String> reserveAudiences(@PathVariable Integer id, @RequestBody Map<String, String> action,
                                                @AuthenticationPrincipal User user) {
        Map<String, String> result = new HashMap<>();

        if (action.get("action").equalsIgnoreCase(AudienceAction.RESERVE.name())) {
            if (audienceService.reserveAudience(user.getId(), id)) {
                result.put("result", "ok");
            } else {
                result.put("result", "error");
                result.put("message", "Audience has been already reserved!");
            }
        } else if (action.get("action").equalsIgnoreCase(AudienceAction.LEAVE.name())) {
            if (audienceService.endReserveAudience(user.getId(), id)) {
                result.put("result", "ok");
            } else {
                result.put("result", "error");
                result.put("message", "You are not principal");
            }
        }
        return result;
    }
}
