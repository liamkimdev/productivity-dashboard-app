package org.productivity.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.productivity.models.Note;
import org.productivity.models.NoteWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteWidgetJdbcTemplateRepositoryTest {

    @Autowired
    NoteWidgetJdbcTemplateRepository noteWidgetJdbcTemplateRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void createNoteWidget() {
        // Arrange
        NoteWidget noteWidget = new NoteWidget();
        noteWidget.setTitle("Test Note Widget Title");
        noteWidget.setDashboardId(1);

        // Act
        NoteWidget actualNoteWidget = noteWidgetJdbcTemplateRepository.createNoteWidget(noteWidget);

        //Assert
        assertNotNull(actualNoteWidget);
        assertEquals(noteWidget.getTitle(), "Test Note Widget Title");
    }

    @Test
    void deleteByNoteWidgetIdThenShouldNotDeleteNoteWidgetByIdThatHasBeenAlreadyDeleted() {
        assertTrue(noteWidgetJdbcTemplateRepository.deleteNoteWidgetById(1));
        assertFalse(noteWidgetJdbcTemplateRepository.deleteNoteWidgetById(1));
    }

    @Test
    void shouldNotDeleteWithInvalidId() {
        assertFalse(noteWidgetJdbcTemplateRepository.deleteNoteWidgetById(100));
    }
}