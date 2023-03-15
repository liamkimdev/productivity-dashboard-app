package org.productivity.controllers;

import org.productivity.domain.DashboardService;
import org.productivity.domain.Result;
import org.productivity.models.Dashboard;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/{dashboardId}")
    public ResponseEntity<Dashboard> findByDashboardId(@RequestBody int dashboardId){
        Dashboard dashboard = service.findByDashboardId(dashboardId);

        if (dashboard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
