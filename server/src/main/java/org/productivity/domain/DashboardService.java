package org.productivity.domain;

import org.productivity.data.DashboardRepository;
import org.productivity.models.Dashboard;
import org.productivity.models.Note;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public Dashboard findByDashboardName(String dashboardName) {
        return dashboardRepository.findByDashboardName(dashboardName);
    }

    public Result<Dashboard> createDashboard(Dashboard dashboard) {
        Result<Dashboard> result = validate(dashboard);

        if (!result.isSuccess()) {
            return result;
        }

        dashboard = dashboardRepository.createDashboard(dashboard);
        result.setPayload(dashboard);
        return result;
    }

    public Result<Dashboard> updateDashboard(Dashboard dashboard) {
        Result<Dashboard> result = validate(dashboard);

        if (!result.isSuccess()) {
            return result;
        }

        if (dashboard.getDashboardId() <= 0) {
            result.addMessage(ResultType.INVALID, "dashboardId must be set for the `update` operation");
        }

        if (!dashboardRepository.updateDashboard(dashboard)) {
            result.addMessage(ResultType.NOT_FOUND, String.format("dashboardId %s not found", dashboard.getDashboardId()));
        }

        return result;
    }

    public boolean deleteDashboardById (int dashboardId) {
        return dashboardRepository.deleteDashboardById(dashboardId);
    }

    private Result<Dashboard> validate(Dashboard dashboard) {
        Result<Dashboard> result = new Result<>();

        if (dashboard == null) {
            result.addMessage(ResultType.INVALID, "Dashboard cannot be null.");
            return result;
        }

        if (Validations.isNullOrBlank(dashboard.getDashboardName())) {
            result.addMessage(ResultType.INVALID, "Dashboard name is required.");
        }

        if (dashboard.getUserId() == 0) {
            result.addMessage(ResultType.INVALID, "User Id is required.");
        }

        return result;
    }

}
