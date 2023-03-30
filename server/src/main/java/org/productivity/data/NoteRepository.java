package org.productivity.data;

import org.productivity.models.Note;
import java.time.LocalDate;

import java.util.List;

public interface NoteRepository {

    List<Note> findAll();

    List<Note> findByNoteDate(LocalDate date);

    Note findByNoteId(int noteId);

    List<Note> findByNoteDescription(String description);

    Note createNote(Note note);

    boolean updateNote(Note note);

    boolean deleteNoteById(int noteId);
}
