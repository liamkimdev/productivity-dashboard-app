package org.productivity.models;

import java.util.List;

public class Dashboard {

    private int dashboardId;

    private String dashboardName;

    private int userId;

    private List<Object> allWidgets;

    public Dashboard(){}

    public Dashboard(int dashboardId, String dashboardName, int userId, List<Object> allWidgets) {
        this.dashboardId = dashboardId;
        this.dashboardName = dashboardName;
        this.userId = userId;
        this.allWidgets = allWidgets;
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

    public List<Object> getAllWidgets() {
        return allWidgets;
    }

    public void setAllWidgets(List<Object> allWidgets) {
        this.allWidgets = allWidgets;
    }



}
