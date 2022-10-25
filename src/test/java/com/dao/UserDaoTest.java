package com.dao;

import com.domain.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new DaoFactory().userDao();
        // DaoFactory.class를 만들어서 책임을 분리했기에,
        // Test.class에선 UserDao가 어떻게 만들어 지는지 신경쓰지 않고
        // Factory에서 UserDao 오브젝트를 받아다가, 활용만 하면된다.

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