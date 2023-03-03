package org.productivity.data;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.productivity.data.mappers.NoteMapper;
import org.productivity.domain.NoteService;
import org.productivity.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 4; // change next_id number

    @Autowired
    NoteJdbcTemplateRepository noteJdbcTemplateRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllNotes() {
        // Arrange
        List<Note> notes = new ArrayList<>();

        // Act
        notes = noteJdbcTemplateRepository.findAll();

        // Assert
        assertTrue(notes.size() >= 3 && notes.size() <= 5);
    }

    @Test
    void shouldFindByNoteDate() {
        // Arrange
        List<Note> notes = noteJdbcTemplateRepository.findByNoteDate(LocalDate.of(2023,3,3));

        // Assert
        assertNotNull(notes);
        assertEquals(3, notes.size());
    }

    @Test
    void shouldFindByNoteId() {
        Note note = noteJdbcTemplateRepository.findByNoteId(1);

        assertEquals(1, note.getNoteId());
        assertEquals("Title Test One", note.getTitle());
        assertEquals("Description test one", note.getDescription());
    }

    @Test
    void shouldFindByNoteDescription() {
        List<Note> actual = noteJdbcTemplateRepository.findByNoteDescription("Today I ate an apple.");

        assertEquals(1, actual.size());
        assertNotNull(actual);
    }

    @Test
    void shouldCreateNote() {
        // Arrange
        Note note = new Note();
        note.setTitle("Test Title");
        note.setDescription("Test Description");
        note.setDate(LocalDate.of(2023,3,3));
        note.setNoteWidget(1);

        // Act
        Note actualNote = noteJdbcTemplateRepository.createNote(note);

        //Assert
        assertNotNull(actualNote);
        assertEquals(note.getTitle(), "Test Tile");
    }

    @Test
    void shouldUpdateNote() {
        Note actual = noteJdbcTemplateRepository.findByNoteId(1);

        actual.setDescription("This is a test");

        assertTrue(noteJdbcTemplateRepository.updateNote(actual));
        assertEquals(noteJdbcTemplateRepository.findByNoteId(1).getDescription(), "This is a test");
    }

    @Test
    void shouldDeleteNoteById(){
        assertTrue(noteJdbcTemplateRepository.deleteNoteById(2));
    }

    @Test
    void shouldNotDeleteNoteByIdThatHasBeenAlreadyDeleted() {
        assertTrue(noteJdbcTemplateRepository.deleteNoteById(2));
    }

    @Test
    void shouldNotDeleteWitInvalidID(){
        assertFalse(noteJdbcTemplateRepository.deleteNoteById(78));
    }
}