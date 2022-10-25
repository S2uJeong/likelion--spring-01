package com.dao;

import com.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public UserDao() {
    }

    public void deleteAll() {

        this.jdbcTemplate.update("delete from users");
    }

    public void add(User user) {

        this.jdbcTemplate.update("INSERT INTO users(id, name, password) values(?,?,?)"
                , user.getId(), user.getName(), user.getPassword()
        );
    }

    public User findById(String id) {
        String sql = "select * from users where id =?";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password")
                );
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
    }


}



