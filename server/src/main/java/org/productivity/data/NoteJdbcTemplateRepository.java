package org.productivity.data;

import org.productivity.data.mappers.NoteMapper;
import org.productivity.models.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class NoteJdbcTemplateRepository implements NoteRepository{

    private final JdbcTemplate jdbcTemplate;

    public NoteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Note> findAll() {
        final String sql = "SELECT * from production.note;";

        List<Note> notes = jdbcTemplate.query(sql, new NoteMapper());
        return notes;
    }

    @Override
    public List<Note> findByNoteDate(LocalDate date) {
        final String sql = "SELECT * from production.note WHERE `date` = ?;";

        List<Note> note = jdbcTemplate.query(sql, new NoteMapper(), date);
        return note;
    }

    @Override
    public Note findByNoteId(int noteId) {
        final String sql = "SELECT * from note WHERE note_id = ?;";

        Note note = jdbcTemplate.query(sql, new NoteMapper(), noteId).stream()
                .findAny().orElse(null);

        return note;
    }

    @Override
    public List<Note> findByNoteDescription(String description) {
        final String sql = "SELECT * from production.note WHERE description = %?%;";

        List<Note> notesList = jdbcTemplate.query(sql, new NoteMapper(), description);

        return notesList;
    }

    @Override
    public Note createNote(Note note) {
        final String sql = "INSERT into production.note (title, description, `date`) "
                    + "values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getDescription());
            ps.setString(3, String.valueOf(note.getDate()));
            return ps;
            }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        note.setNoteId(keyHolder.getKey().intValue());
        return note;
    }

    @Override
    public boolean updateNote(Note note) {

        final String sql = "UPDATE production.note SET "
                + "title = ?, "
                + "description = ?, "
                + "`date` = ? "
                + "WHERE note_id = ?;";

        boolean updateConfirmation = jdbcTemplate.update(sql,
                note.getTitle(),
                note.getDescription(),
                note.getDate(),
                note.getNoteId()) > 0;

        return updateConfirmation;
    }

    @Override
    public boolean deleteNoteById(int noteId) {

        final String sql = "DELETE from production.note WHERE note_id = ?;";

        boolean deleteConfirmation = jdbcTemplate.update(sql,
                noteId) > 0;

        return deleteConfirmation;
    }
}
