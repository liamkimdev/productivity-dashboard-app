package org.productivity.models;

import java.time.LocalDate;

public class Note {
    private int noteId;
    private String title;
    private String description;
    private LocalDate date;
    private int dashboardId;

    public Note() {
    }

    public Note(int noteId, String title, String description, LocalDate date, int dashboardId) {
        this.noteId = noteId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.dashboardId = dashboardId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(int dashboardId) {
        this.dashboardId = dashboardId;
    }
}
