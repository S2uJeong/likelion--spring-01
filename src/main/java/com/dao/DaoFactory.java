package com.dao;

public class DaoFactory {

    /*  이는 DaoFactory 안에서 다양한 메서드를 활용할 땐 안좋은 코드다. 이유: 새로운 객체 생성을 계속함.
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
    */

    // 아래와 같이 바꿔준다.

    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public UserDao accountDao() {
        return new UserDao(connectionMaker());
    }


}
