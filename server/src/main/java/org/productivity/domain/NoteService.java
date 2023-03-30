package org.productivity.domain;

import org.productivity.data.NoteJdbcTemplateRepository;
import org.productivity.data.NoteRepository;
import org.productivity.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Not;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDate;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public List<Note> findByNoteDate(LocalDate date) {
        return noteRepository.findByNoteDate(date);
    }

    public Note findByNoteId(int noteId) {
        return noteRepository.findByNoteId(noteId);
    }

    public List<Note> findByNoteDescription(String description) {
        return noteRepository.findByNoteDescription(description);
    }

    public Result<Note> createNote(Note note) {
        Result<Note> result = validate(note);

        if (!result.isSuccess()) {
            return result;
        }

        Note createdNote = noteRepository.createNote(note);
        result.setPayload(createdNote);

        return result;
    }

    public Result<Note> updateNote(Note note) {
        Result<Note> result = validate(note);

        if (!result.isSuccess()) {
            return result;
        }

        if (note.getNoteId() <= 0) {
            result.addMessage(ResultType.INVALID, "noteId must be set for the `update` operation");
        }

        if (!noteRepository.updateNote(note)) {
            result.addMessage(ResultType.NOT_FOUND, String.format("noteId %s not found", note.getNoteId()));
        }

        return result;
    }

    public boolean deleteNoteById(int noteId) {
        return noteRepository.deleteNoteById(noteId);
    }

    private Result<Note> validate (Note note) {
        Result<Note> result = new Result<>();

        if (note == null) {
            result.addMessage(ResultType.INVALID, "Note cannot be null.");
            return result;
        }

        if (note.getDate() == null) {
            result.addMessage(ResultType.INVALID, "Timestamp is required.");
        }

        if (note.getNoteWidget() <= 0) {
            result.addMessage(ResultType.INVALID, "Note Widget Id is required.");
        }

        return result;
    }
}
