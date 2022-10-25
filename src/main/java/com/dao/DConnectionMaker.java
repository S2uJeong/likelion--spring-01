package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.dao.ConnectionConst.*;

public class DConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return conn;
    }
}
