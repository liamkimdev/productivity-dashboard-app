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
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.sql.Timestamp;
import java.util.Map;

@Repository
public class NoteJdbcTemplateRepository implements NoteRepository{

    private final JdbcTemplate jdbcTemplate;

    public NoteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Note> findAll() {
        final String sql = "SELECT * from note;";

        List<Note> notes = jdbcTemplate.query(sql, new NoteMapper());
        return notes;
    }

    @Override
    public List<Note> findByNoteDate(LocalDateTime date) {
        final String sql = "SELECT * from note WHERE \"date\" = ?;";

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
        final String sql = "SELECT * from note WHERE description ILIKE '%' || ? || '%';";

        List<Note> notesList = jdbcTemplate.query(sql, new NoteMapper(), description);

        return notesList;
    }

    @Override
    public Note createNote(Note note) {
        final String sql = "INSERT into note (title, description, \"date\", note_widget_id) "
                    + "values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getDescription());
            ps.setTimestamp(3, new Timestamp(note.getDate().toEpochSecond(ZoneOffset.UTC)));
            ps.setInt(4, note.getNoteWidget());

            return ps;
            }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        Map<String, Object> keys = keyHolder.getKeys();
        int noteId = (int) keys.get("note_id");
        note.setNoteId(noteId);

        return note;
    }

    @Override
    public boolean updateNote(Note note) {

        final String sql = "UPDATE note SET "
                + "title = ?, "
                + "description = ?, "
                + "\"date\" = ? "
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

        final String sql = "DELETE from note WHERE note_id = ?;";

        boolean deleteConfirmation = jdbcTemplate.update(sql,
                noteId) > 0;

        return deleteConfirmation;
    }
}
