package org.productivity.data;

import org.productivity.models.Dashboard;

public interface DashboardRepository {

    Dashboard findByDashboardName(String dashboardName);

    Dashboard addDashboard(Dashboard dashboard);

    boolean updateDashboardById(String name, int dashboardId);

    boolean deleteDashboardById (int dashboardId);
}
