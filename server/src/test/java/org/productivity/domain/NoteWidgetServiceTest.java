package org.productivity.domain;

import org.junit.jupiter.api.Test;
import org.productivity.data.NoteJdbcTemplateRepository;
import org.productivity.data.NoteWidgetJdbcTemplateRepository;
import org.productivity.models.Note;
import org.productivity.models.NoteWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteWidgetServiceTest {

    @MockBean
    NoteWidgetJdbcTemplateRepository repository;

    @Autowired
    NoteWidgetService service;

    @Test
    void shouldCreateNoteWidget() {
        NoteWidget noteWidget = new NoteWidget(1, 1, "My Note Widget");

        when(repository.createNoteWidget(noteWidget)).thenReturn(noteWidget);

        NoteWidget result = repository.createNoteWidget(noteWidget);

        verify(repository).createNoteWidget(noteWidget);

        assertEquals(noteWidget, result);

    }

    @Test
    void shouldNotCreateNoteWidgetWithNullNoteWidget() {
        NoteWidget noteWidget = null;

        Result<NoteWidget> result = service.createNoteWidget(noteWidget);

        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
        assertEquals("Note widget cannot be null.", result.getMessages().get(0));
    }

    @Test
    void shouldNotCreateNoteWidgetWithNonExistingDashboard() {
        NoteWidget noteWidget = new NoteWidget(1, 0, "My Note Widget");

        when(repository.createNoteWidget(noteWidget)).thenReturn(null);

        Result<NoteWidget> result = service.createNoteWidget(noteWidget);

        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
        assertEquals("Dashboard Id is required.", result.getMessages().get(0));

    }
}