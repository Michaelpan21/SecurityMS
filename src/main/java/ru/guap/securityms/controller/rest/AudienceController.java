package ru.guap.securityms.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.guap.securityms.domain.User;
import ru.guap.securityms.service.AudienceService;

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
    public Map<String, String> reserveAudiences(@PathVariable Long id, @AuthenticationPrincipal User user) {
        audienceService.reserveAudience(user.getId(), id);
        return audienceService.getAudienceById(id);
    }
}
