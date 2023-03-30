package org.productivity.domain;

import org.junit.jupiter.api.Test;
import org.productivity.data.NoteJdbcTemplateRepository;
import org.productivity.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteServiceTest {

    @MockBean
    NoteJdbcTemplateRepository repository;

    @Autowired
    NoteService service;

    @Test
    void shouldFindByNoteDescription() {
        List<Note> expected = new ArrayList<>();

        Note actual = createNote();
        expected.add(actual);

        when(repository.findByNoteDescription("Today I had a great")).thenReturn(expected);

        assertEquals(1, expected.size());
        assertEquals("A Glorious Day", expected.get(0).getTitle());
    }

    @Test
    void shouldNotFindNoteWithBadDescription() {
        String badDescription = "non-existent note description";

        when(repository.findByNoteDescription(badDescription)).thenReturn(new ArrayList<>());

        List<Note> actualNotes = repository.findByNoteDescription(badDescription);

        assertTrue(actualNotes.isEmpty());
    }

    @Test
    void shouldCreateNoteWithValidInputs() {
        Note note = createNote();
        Note mockNote = createMockNote();

        when(repository.createNote(note)).thenReturn(mockNote);

        Result<Note> actual = service.createNote(note);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockNote, actual.getPayload());
    }

    @Test
    void shouldNotCreateNoteWithNullNote() {
        Note note = new Note();
        Note mockNote = new Note();

        when(repository.createNote(note)).thenReturn(mockNote);

        Result<Note> actual = service.createNote(note);

        assertEquals(ResultType.INVALID, actual.getType());
        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotCreateNoteNullOrBlankDate() {
        Note note = new Note(3, "Mock", "Mock", null, 1);

        Result<Note> actual = service.createNote(note);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotCreateNoteWithNonExistingNoteWidgetId() {
        Note note = new Note();
        note.setNoteId(1);
        note.setDescription("Today I had a great day and saw the sun!");
        note.setTitle("A Glorious Day");
        note.setDate(LocalDate.of(1, 1, 1));

        Result<Note> actual = service.createNote(note);

        assertEquals("Note Widget Id is required.", actual.getMessages().get(0));
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void updateNoteWithValidInputs() {
        Note note = createNote();
        when(repository.findByNoteId(1)).thenReturn(note);

        Note updatedNote = new Note(1, "Updated", "Updated", LocalDate.of(1, 1, 1), 1);

        when(repository.updateNote(updatedNote)).thenReturn(true);

        Result<Note> actual = service.updateNote(updatedNote);

        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateNoteIfNoteIdIsNotFound() {
        Note note = createNote();
        when(repository.findByNoteId(1)).thenReturn(note);
        note.setNoteId(999999);

        Result<Note> updatedNote = service.updateNote(note);

        assertEquals(ResultType.NOT_FOUND, updatedNote.getType());
    }

    private Note createNote() {
        Note note = new Note();
        note.setNoteId(1);
        note.setDescription("Today I had a great day and saw the sun!");
        note.setTitle("A Glorious Day");
        note.setNoteWidget(1);
        note.setDate(LocalDate.of(1, 1, 1));

        return note;
    }

    private Note createMockNote() {
        Note note = new Note();
        note.setNoteId(2);
        note.setDescription("Mock Today I had a great day and saw the sun!");
        note.setTitle("Mock A Glorious Day");
        note.setNoteWidget(1);
        note.setDate(LocalDate.of(1, 1, 1));

        return note;
    }
}