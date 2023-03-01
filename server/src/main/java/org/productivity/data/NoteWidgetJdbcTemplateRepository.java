package org.productivity.data;

import org.productivity.models.NoteWidget;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class NoteWidgetJdbcTemplateRepository implements NoteWidgetRepository{

    private final JdbcTemplate jdbcTemplate;

    public NoteWidgetJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public NoteWidget createNoteWidget(NoteWidget noteWidget) {
        final String sql = "INSERT into note_widget (title) "
                + "values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, noteWidget.getTitle());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        noteWidget.setNoteWidgetId(keyHolder.getKey().intValue());
        return noteWidget;
    }

    @Override
    public boolean deleteByNoteWidgetId(int noteWidgetId) {
        final String sql = "DELETE from note_widget WHERE note_widget_id = ?;";

        boolean deleteConfirmation = jdbcTemplate.update(sql, noteWidgetId) > 0;

        return deleteConfirmation;
    }
}
