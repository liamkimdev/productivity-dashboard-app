package org.productivity.data;

import org.productivity.models.NoteWidget;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@Repository
public class NoteWidgetJdbcTemplateRepository implements NoteWidgetRepository{

    private final JdbcTemplate jdbcTemplate;

    public NoteWidgetJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public NoteWidget createNoteWidget(NoteWidget noteWidget) {
        final String sql = "INSERT into note_widget (title, dashboard_id) "
                + "values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, noteWidget.getTitle());
            ps.setInt(2, noteWidget.getDashboardId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        Map<String, Object> keys = keyHolder.getKeys();
        int noteWidgetId = (int) keys.get("note_widget_id");
        noteWidget.setNoteWidgetId(noteWidgetId);

        return noteWidget;
    }

    @Override
    @Transactional
    public boolean deleteNoteWidgetById(int noteWidgetId) {

        jdbcTemplate.update("DELETE from note WHERE note_widget_id = ?;", noteWidgetId);

        return jdbcTemplate.update("DELETE from note_widget WHERE note_widget_id = ?;", noteWidgetId) > 0;
    }
}
