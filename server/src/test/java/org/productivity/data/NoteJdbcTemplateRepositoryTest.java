package org.productivity.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.productivity.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(notes.size() >= 2 && notes.size() <= 4);
    }

    @Test
    void shouldFindByNoteDate() {
        // Arrange
        List<Note> notes = noteJdbcTemplateRepository.findByNoteDate(LocalDate.of(2023,3,4));

        // Assert
        assertNotNull(notes);
        assertEquals(1, notes.size());
    }

    @Test
    void shouldFindByNoteId() {
        Note note = noteJdbcTemplateRepository.findByNoteId(3);

        assertEquals(3, note.getNoteId());
        assertEquals("Title Test Three", note.getTitle());
        assertEquals("Description test three", note.getDescription());
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
        note.setDate(LocalDateTime.of(2023,3,3,0,0,0,0));
        note.setNoteWidget(1);

        // Act
        Note actualNote = noteJdbcTemplateRepository.createNote(note);

        //Assert
        assertNotNull(actualNote);
        assertEquals(note.getTitle(), "Test Title");
    }

    @Test
    void shouldUpdateNote() {
        Note actual = noteJdbcTemplateRepository.findByNoteId(1);

        actual.setDescription("This is a test");

        assertTrue(noteJdbcTemplateRepository.updateNote(actual));
        assertEquals(noteJdbcTemplateRepository.findByNoteId(1).getDescription(), "This is a test");
    }

    @Test
    void shouldDeleteNoteByIdThenShouldNotDeleteNoteByIdThatHasBeenAlreadyDeleted(){
        assertTrue(noteJdbcTemplateRepository.deleteNoteById(1));
        assertFalse(noteJdbcTemplateRepository.deleteNoteById(1));
    }

    @Test
    void shouldNotDeleteWitInvalidID(){
        assertFalse(noteJdbcTemplateRepository.deleteNoteById(78));
    }
}