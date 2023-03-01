package org.productivity.data.mappers;

import org.productivity.models.Note;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet rs, int i) throws SQLException {
        Note note = new Note();
        note.setNoteId(rs.getInt("note_id"));
        note.setTitle(rs.getString("title"));
        note.setDescription(rs.getString("description"));
        note.setDate(rs.getDate("timestamp").toLocalDate());
        note.setNoteWidget(rs.getInt("note_widget_id"));

        return note;
    }
}
