package com.dao;

import com.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        // 생성자를 통해 간결하게 표현하게 수정, user을 늘려보았다.
        User user1 = new User("1", "sujeong1", "password1");
        User user2 = new User("2", "sujeong2", "password2");
        User user3 = new User("3", "sujeong3", "password3");

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        // get 조회메서드 테스트 개선
        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName()).isEqualTo(user1.getName());

    }    

}