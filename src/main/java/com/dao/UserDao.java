package com.dao;

import com.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
      this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
          "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) { // 쿼리의 결과가 있으면 User 오브젝트를 만들고 값을 넣는다.
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        conn.close();

        // 결과가 없으면 User은 null 상태 그대로이다. 이를 확인해서 예외를 던진다.
        if (user==null) throw new EmptyResultDataAccessException(1);


        return user;

    }

    public void deleteAll() throws SQLException, ClassNotFoundException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("delete from users");
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        conn.close();

        return count;
    }


}
