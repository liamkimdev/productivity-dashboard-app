package org.productivity.data;

import org.productivity.data.mappers.DashboardMapper;
import org.productivity.models.Dashboard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class DashboardJdbcTemplateRepository implements DashboardRepository {

    private final JdbcTemplate jdbcTemplate;

    public DashboardJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dashboard findByDashboardName(String dashboardName) {
        final String sql = "SELECT * from note WHERE dashboard_name = ?;";

        Dashboard dashboard = jdbcTemplate.query(sql, new DashboardMapper(), dashboardName).stream()
                .findAny().orElse(null);

        return dashboard;
    }

    @Override
    public Dashboard createDashboard(Dashboard dashboard) {
        final String sql = "INSERT into dashboard (dashboard_name, user_id)"
                + " values (?, ?, ?);";

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

        dashboard.setDashboardId(keyHolder.getKey().intValue());
        return dashboard;
    }

    @Override
    public boolean updateDashboard(Dashboard dashboard) {

        final String sql = "UPDATE dashboard SET "
                + "dashboard_name = ? "
                + "WHERE dashboard_id =?;";

        boolean updateConfirmation =  jdbcTemplate.update(sql,
                dashboard.getDashboardName(),
                dashboard.getDashboardId()) > 0;

        return updateConfirmation;
    }

    @Override
    public boolean deleteDashboardById(int dashboardId) {

        final String sql = "DELETE from dashboard WHERE dashboard_id  = ?;";

        boolean deleteConfirmation = jdbcTemplate.update(sql,
                dashboardId) > 0;

        return deleteConfirmation;
    }
}
