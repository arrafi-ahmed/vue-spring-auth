package com.example.vuespringauth.repository;

import com.example.vuespringauth.model.Role;
import com.example.vuespringauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User findUserByUsername(String username) {
        String sql = "select * from users where username=?;";
        Object[] args = {username};

        try {
//            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), args);
            return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setCreateTime(rs.getObject("create_time", OffsetDateTime.class));
                    user.setRole(Role.getRole(rs.getString("role")));
                    System.out.println(user);
                    return user;
                }
            }, args);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int saveUser(User user) {
        String sql = "insert into users (username, password, name, create_time, role) values(?,?,?,?,?);";
        Object[] args = {user.getUsername(), user.getPassword(), user.getName(), user.getCreateTime(),
                user.getRole().getString()};
        return jdbcTemplate.update(sql, args);
    }
}
