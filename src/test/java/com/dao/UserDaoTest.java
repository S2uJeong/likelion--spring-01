package com.dao;

import com.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 설정정보가 들어있는 DaoFactory class를 ApplicationContext로 넘긴다. (코드 사용방법 외우기)
        // getBean()은 DaoFactory 안에 있는 bean(객체)를 가져오는 ApplicationContext의 메서드
        // 'userDao'는 자동생성(default)되는 bean의 이름이다. bean 클래스명에서 앞글자만 소문자로 바꾸는게 규칙이다.
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);


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