package org.productivity.data;

import org.productivity.models.Dashboard;

public interface DashboardRepository {

    Dashboard findByDashboardId(int dashboardId);

    Dashboard findDashboardByUserId(int userId);

    Dashboard createDashboard(Dashboard dashboard);

    boolean updateDashboard(Dashboard dashboard);
}
