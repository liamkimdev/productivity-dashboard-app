package org.productivity.models;

public class Dashboard {
    private int dashboardId;
    private String dashboardName;
    private int userId;

    public Dashboard() {
    }

    public Dashboard(int dashboardId, String dashboardName, int userId) {
        this.dashboardId = dashboardId;
        this.dashboardName = dashboardName;
        this.userId = userId;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getDashboardName() {
        return dashboardName;
    }

    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
