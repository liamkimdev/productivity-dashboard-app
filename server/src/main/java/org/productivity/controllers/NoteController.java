package org.productivity.controllers;

import com.nimbusds.oauth2.sdk.Response;
import org.productivity.data.NoteJdbcTemplateRepository;
import org.productivity.domain.NoteService;
import org.productivity.domain.Result;
import org.productivity.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})

@RequestMapping("/note")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Note> findAll() {
        return service.findAll();
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<Note>> findByNoteDate(@PathVariable LocalDate date) {
        List<Note> notes = service.findByNoteDate(date);

        if(date == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{noteId}")

    //
    //    Note findByNoteId(int noteId);
    //
    //    List<Note> findByNoteDescription(String description);
    //
    //    Note createNote(Note note);

    @PutMapping("/{noteId}")
    public ResponseEntity<Object> updateNote(@PathVariable int noteId, @RequestBody Note note) {
        if (noteId != note.getNoteId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Note> result = service.updateNote(note);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable int noteId) {
        if (service.deleteNoteById(noteId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
