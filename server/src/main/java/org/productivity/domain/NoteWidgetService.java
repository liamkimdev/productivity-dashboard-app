package org.productivity.domain;

import org.productivity.data.NoteWidgetRepository;
import org.productivity.models.NoteWidget;
import org.springframework.stereotype.Service;

@Service
public class NoteWidgetService {

    private final NoteWidgetRepository noteWidgetRepository;

    public NoteWidgetService(NoteWidgetRepository noteWidgetRepository) {
        this.noteWidgetRepository = noteWidgetRepository;
    }

    public Result<NoteWidget> createNoteWidget(NoteWidget noteWidget) {

        Result<NoteWidget> result = validate(noteWidget);

        if (!result.isSuccess()) {
            return result;
        }

        noteWidget = noteWidgetRepository.createNoteWidget(noteWidget);
        result.setPayload(noteWidget);

        return result;
    }

    public boolean deleteByNoteWidgetId(int noteWidgetId) {
        return noteWidgetRepository.deleteByNoteWidgetId(noteWidgetId);
    }

    private Result<NoteWidget> validate(NoteWidget noteWidget) {
        Result<NoteWidget> result = new Result<>();

        if (noteWidget == null) {
            result.addMessage(ResultType.INVALID, "Note widget cannot be null.");
            return result;
        }

        if (Validations.isNullOrBlank(String.valueOf(noteWidget.getDashboardId()))) {
            result.addMessage(ResultType.INVALID, "Dashboard Id is required.");
        }

        return result;
    }
}
