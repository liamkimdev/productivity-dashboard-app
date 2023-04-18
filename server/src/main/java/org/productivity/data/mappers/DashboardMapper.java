package org.productivity.data.mappers;

import org.productivity.models.Dashboard;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DashboardMapper implements RowMapper<Dashboard> {

    @Override
    public Dashboard mapRow(ResultSet rs, int i) throws SQLException {
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardId(rs.getInt("dashboard_id"));
        dashboard.setDashboardName(rs.getString("dashboard_name"));
        dashboard.setUserId(rs.getInt("user_id"));
        return dashboard;
    }
}
