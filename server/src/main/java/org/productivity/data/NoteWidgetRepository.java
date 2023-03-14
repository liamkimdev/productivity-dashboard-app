package org.productivity.data;

import org.productivity.models.Note;
import org.productivity.models.NoteWidget;

import java.time.LocalDate;
import java.util.List;

public interface NoteWidgetRepository {

    NoteWidget createNoteWidget(NoteWidget noteWidget);

    boolean deleteNoteWidgetById(int noteWidgetId);
}
