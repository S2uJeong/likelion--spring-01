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
    // DB의 상태가 매번 달라지고, 테스트를 위해 DB를 특정 상태로 만들어 줄 수 없다면 단위 테스트로서 가치가 없다.
    // 기존 UserDaoTest를 단위테스트로써 가치가 있도록 개선해보겠다.

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll(); // test 실행전 DB 초기화 해준다. (primary key 충돌 방지 가능)
        assertThat(dao.getCount()).isEqualTo(0);

        User user = new User();
        user.setId("1");
        user.setName("sujeong");
        user.setPassword("password");

        dao.add(user);
        // delete 후 0 개 였던게, add를 해주었으니 1이 되어야 한다. test!
        assertThat(dao.getCount()).isEqualTo(1);

        User user2 = dao.get(user.getId());

        assertThat(user2.getName()).isEqualTo(user.getName());
        assertThat(user2.getPassword()).isEqualTo(user.getPassword());

        /* Junit 도입 전, 쓰던 test 방법. 이젠 필요옶다 ㅠ
         if (!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        } else if (!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        } else {
            System.out.println("조회 테스트 성공");
        }*/
    }    

}