package org.productivity.controllers;

import org.productivity.domain.NoteWidgetService;
import org.productivity.domain.Result;
import org.productivity.models.NoteWidget;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard/noteWidget")
public class NoteWidgetController {

    private final NoteWidgetService service;

    public NoteWidgetController(NoteWidgetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createNoteWidget(@RequestBody NoteWidget noteWidget) {
        Result<NoteWidget> result = service.createNoteWidget(noteWidget);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }


    @DeleteMapping("/{noteWidgetId}")
    public ResponseEntity<Void> deleteNoteWidgetById(@PathVariable int noteWidgetId) {
        if (service.deleteByNoteWidgetId(noteWidgetId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
