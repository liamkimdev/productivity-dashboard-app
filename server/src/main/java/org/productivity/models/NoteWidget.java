package org.productivity.models;

public class NoteWidget {

    private int noteWidgetId;

    private String title;

    private int dashboardId;

    public NoteWidget() {
    }

    public NoteWidget(int noteWidgetId, int dashboardId, String title) {
        this.noteWidgetId = noteWidgetId;
        this.dashboardId = dashboardId;
        this.title = title;
    }

    public int getNoteWidgetId() {
        return noteWidgetId;
    }

    public void setNoteWidgetId(int noteWidgetId) {
        this.noteWidgetId = noteWidgetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }
}
