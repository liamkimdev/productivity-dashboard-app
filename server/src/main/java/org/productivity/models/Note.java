package org.productivity.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class Note {
    private int noteId;
    private String title;
    private String description;
    private LocalDate date;
    private int noteWidgetId;

    public Note() {
    }

    public Note(int noteId, String title, String description, LocalDate date, int noteWidgetId) {
        this.noteId = noteId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.noteWidgetId = noteWidgetId;
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

    public int getNoteWidget() {
        return noteWidgetId;
    }

    public void setNoteWidget(int noteWidgetId) {
        this.noteWidgetId = noteWidgetId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
