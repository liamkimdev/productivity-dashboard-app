package org.productivity.models;

public class Dashboard {
    private int dashboardId;
    private boolean permission;

    public Dashboard() {
    }

    public Dashboard(int dashboardId, boolean permission) {
        this.dashboardId = dashboardId;
        this.permission = permission;
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

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
