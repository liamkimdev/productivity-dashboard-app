package org.productivity.data;

import org.productivity.models.Dashboard;

public interface DashboardRepository {

    Dashboard findByDashboardName(String dashboardName);

    Dashboard createDashboard(Dashboard dashboard);

    boolean updateDashboard(Dashboard dashboard);

    boolean deleteDashboardById (int dashboardId);
}
