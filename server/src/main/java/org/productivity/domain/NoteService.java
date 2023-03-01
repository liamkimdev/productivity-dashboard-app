package org.productivity.domain;

import org.productivity.data.NoteRepository;
import org.productivity.models.Note;

import javax.xml.transform.Result;

public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

//    public Result<Note> createNote(Note note){
//        Result<Note> result = validate(note);
//        if(!result.is)
//    }

}
