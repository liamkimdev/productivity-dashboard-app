package org.productivity.data;

import org.productivity.data.mappers.DashboardMapper;
import org.productivity.models.Dashboard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@Repository
public class DashboardJdbcTemplateRepository implements DashboardRepository {

    private final JdbcTemplate jdbcTemplate;

    public DashboardJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dashboard findByDashboardId(int dashboardId) {
        final String sql = "SELECT * from dashboard WHERE dashboard_id = ?;";

        Dashboard dashboard = jdbcTemplate.query(sql, new DashboardMapper(), dashboardId).stream()
                .findAny().orElse(null);

        return dashboard;
    }

    @Override
    public Dashboard createDashboard(Dashboard dashboard) {
        final String sql = "INSERT into dashboard (dashboard_name, user_id)"
                + " values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dashboard.getDashboardName());
            ps.setInt(2, dashboard.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        Map<String, Object> keys = keyHolder.getKeys();
        int dashboardId = (int) keys.get("dashboard_id");
        dashboard.setDashboardId(dashboardId);

        return dashboard;
    }

    @Override
    public boolean updateDashboard(Dashboard dashboard) {

        final String sql = "UPDATE dashboard SET "
                + "dashboard_name = ? "
                + "WHERE dashboard_id = ?;";

        boolean updateConfirmation =  jdbcTemplate.update(sql,
                dashboard.getDashboardName(),
                dashboard.getDashboardId()) > 0;

        return updateConfirmation;
    }
}
