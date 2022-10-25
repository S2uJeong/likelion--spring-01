package com.dao;

import com.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private UserDao dao;

    /* 이전에 전역 변수로 빼 두었던 픽스처를 뺀다. (오류발생요인제거)
    private User user1;
    private User user2;
    private User user3;*/
    
    @BeforeEach
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        dao = context.getBean("userDao", UserDao.class);
    }
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        User user1 = new User("1", "sujeong1", "password1");
        User user2 = new User("2", "sujeong2", "password2");

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);
        
         User userget1 = dao.get(user1.getId());
         assertThat(userget1.getName()).isEqualTo(user1.getName());

    }    

}