package org.productivity.data;

import org.productivity.data.mappers.AppUserMapper;
import org.productivity.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class AppUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);

        final String sql = "SELECT user_id, username, password_hash, enabled "
                + "from app_user "
                + "WHERE username = ?;";

        return jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }

    @Transactional
    public AppUser create(AppUser user) {

        final String sql = "INSERT into app_user (username, password_hash, enabled)" +
                "values (?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isEnabled());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

//        user.setAppUserId(keyHolder.getKey().intValue());

        Map<String, Object> keys = keyHolder.getKeys();
        int userId = (int) keys.get("user_id");
        user.setAppUserId(userId);

        updateRoles(user);

        return user;
    }

    @Transactional
    public void update(AppUser user) {

        final String sql = "UPDATE app_user SET "
                + "username = ?, "
                + "enabled = ? "
                + "WHERE user_id = ?;";

        jdbcTemplate.update(sql,
                user.getUsername(), user.isEnabled(), user.getAppUserId());

        updateRoles(user);
    }

    private void updateRoles(AppUser user) {
        // delete all roles, then re-add
        jdbcTemplate.update("DELETE from app_user_role WHERE user_id = ?;", user.getAppUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (GrantedAuthority role : authorities) {
            String sql = "INSERT into app_user_role (user_id, app_role_id) "
                    + "select ?, app_role_id from app_role WHERE \"name\" = ?;";
            int rows = jdbcTemplate.update(sql, user.getAppUserId(), role.getAuthority());
            System.out.println(rows);
        }
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "SELECT r.name "
                + "from app_user_role ur "
                + "inner join app_role r on ur.app_role_id = r.app_role_id "
                + "inner join app_user au on ur.user_id = au.user_id "
                + "WHERE au.username = ?;";

        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);
    }
}
