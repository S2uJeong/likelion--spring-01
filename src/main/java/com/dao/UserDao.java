package com.dao;

import com.domain.User;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao() {
       connectionMaker = new DConnectionMaker();
       // 첫 DConne 객체 생성 시 구현 클래스를 써줘야 한다. (문제점 발견)
    }
    public void add(User user) throws SQLException, ClassNotFoundException {
        // 이 부분에선, 이제 인터페이스에 정의된 메소드를 사용하므로 DB클래스가 바뀐다 해도 메소드 이름이 변경될 걱정이 없다.
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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
