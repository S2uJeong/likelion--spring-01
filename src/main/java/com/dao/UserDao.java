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

public class UserDao {
    // JdbcTemplate는 스프링이 제공하는 JDBC 코드용 기본 템플릿이다.
    // 만들었던 JdbcContext를 대체한다.
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public UserDao() {
    }

    public void deleteAll() {
        // this.jdbcContext.executeSql("delete from users");
        // 이렇게 다른 클래스에서 만든 메서드를 가져오면서 넣었던 쿼리가
        this.jdbcTemplate.update("delete from users"); // 템플릿의 .update() 기능으로 해결된다.
    }

    public void add(User user) {

        this.jdbcTemplate.update("INSERT INTO users(id, name, password) values(?,?,?)"
                , user.getId(), user.getName(), user.getPassword()
        );
    }

    // 간결해진 코드를 통해 새로운 메서드 findById를 만들어본다.
    // RowMapper 인터페이스 구현체를 내부에 넣어주었다. rs를 User에 매핑한다.
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


}



