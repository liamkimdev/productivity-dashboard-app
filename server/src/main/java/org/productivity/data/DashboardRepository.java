package org.productivity.data;

import org.productivity.models.Dashboard;

public interface DashboardRepository {

    Dashboard findByDashboardId(int dashboardId);

    Dashboard createDashboard(Dashboard dashboard);

    boolean updateDashboard(Dashboard dashboard);
}
