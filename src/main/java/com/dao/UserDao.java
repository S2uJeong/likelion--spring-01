package com.dao;

import com.domain.User;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDao() {  // 예제이므로 한 번만 만들어 인스턴스 변수에 저장해두고 메소드에서 사용할것
        simpleConnectionMaker = new SimpleConnectionMaker();
    }

    public void add(User user) throws SQLException {
        Connection conn = simpleConnectionMaker.getConnection();

        PreparedStatement ps = conn.prepareStatement(
          "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());   // 이 명령어 이해가 안감 user.get__()
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws SQLException {
        Connection conn = simpleConnectionMaker.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;

    }

    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("1");
        user.setName("sujeong");
        user.setPassword("password");

        dao.add(user);
        System.out.println(user.getId() + "등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getId() + "조회성공");
    }
}
