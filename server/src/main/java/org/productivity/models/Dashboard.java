package org.productivity.models;

public class Dashboard {
    private int dashboardId;
    private String dashboardName;
    private boolean permission;
    private int userId;

    public Dashboard() {
    }

    public Dashboard(int dashboardId, String dashboardName, boolean permission, int userId) {
        this.dashboardId = dashboardId;
        this.dashboardName = dashboardName;
        this.permission = permission;
        this.userId = userId;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }



    public boolean isPermission() {
        return permission;
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

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
