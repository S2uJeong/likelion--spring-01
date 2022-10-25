package com.dao;

import com.domain.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest { // 클라이언트 역할을 하는 test class

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        // 이제 클라이언트에서만 어떤 DBMaker을 사용할지 고쳐주기만 하면 된다.
        UserDao dao = new UserDao(connectionMaker);
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