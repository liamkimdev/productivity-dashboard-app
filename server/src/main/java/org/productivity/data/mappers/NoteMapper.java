package org.productivity.data.mappers;

import org.productivity.models.Note;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NoteMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet rs, int i) throws SQLException {
        Note note = new Note();
        note.setNoteId(rs.getInt("note_id"));
        note.setTitle(rs.getString("title"));
        note.setDescription(rs.getString("description"));
        note.setDate((rs.getDate("date").toLocalDate()));
        note.setDashboardId(rs.getInt("dashboard_id"));

        return note;
    }
}
