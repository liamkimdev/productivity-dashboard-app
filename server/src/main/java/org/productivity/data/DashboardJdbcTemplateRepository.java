package org.productivity.data;

import org.productivity.data.mappers.DashboardMapper;
import org.productivity.models.Dashboard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Dashboard addDashboard(Dashboard dashboard) {
        final String sql = "insert into dashboard (dashboard_name)" + " values (?);";

        return null;
    }


    @Override
    public boolean updateDashboardById(String name, int dashboardId) {
        final String sql = "update dashboard set dashboard_name = ? where dashboard_id =?;";
        boolean updateConfirmation =  jdbcTemplate.update(sql,name, dashboardId) > 0;
        return updateConfirmation;
    }

    @Override
    public boolean deleteDashboardById(int dashboardId) {
        boolean deleteDashboard = jdbcTemplate.update("delete from dashboard where dashboard_id =?;", dashboardId) > 0;
        return deleteDashboard;
    }
}
