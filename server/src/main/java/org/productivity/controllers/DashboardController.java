package org.productivity.controllers;

import org.productivity.domain.DashboardService;
import org.productivity.domain.Result;
import org.productivity.models.AppUser;
import org.productivity.models.Dashboard;
import org.productivity.security.AppUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService service;
    private final AppUserService appUserService;

    public DashboardController(DashboardService service, AppUserService appUserService) {
        this.service = service;
        this.appUserService = appUserService;
    }

    @GetMapping("/{dashboardId}")
    public ResponseEntity<Dashboard> findByDashboardId(@PathVariable int dashboardId){
        Dashboard dashboard = service.findByDashboardId(dashboardId);

        if (dashboard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(dashboard);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<Dashboard> findByUserId(@PathVariable int userId){
//        Dashboard dashboard = service.findByUserId(userId);
//
//        if (dashboard == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(dashboard);
//    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Dashboard> findByUserId(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int userId) {
        String currentUsername = userDetails.getUsername();
        AppUser currentUser = appUserService.loadUserByUsername(currentUsername);

        Dashboard dashboard = service.findByUserId(userId);

        if (dashboard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

        if (currentUser.getAppUserId() != userId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
         }

        return ResponseEntity.ok(dashboard);
    }

    @PostMapping
    public ResponseEntity<Object> createDashboard(@RequestBody Dashboard dashboard) {
        Result<Dashboard> result = service.createDashboard(dashboard);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{dashboardId}")
    public ResponseEntity<Object> updateDashboard(@PathVariable int dashboardId, @RequestBody Dashboard dashboard) {

        if (dashboardId != dashboard.getDashboardId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Dashboard> result = service.updateDashboard(dashboard);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

}
