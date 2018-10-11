package com.sport.team;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String DB_DRIVER ="com.mysql.jdbc.Driver";
    private static String DB_URL ="jdbc:mysql://localhost:3306/sportteam";
    private static String DB_USERNAME ="root";
    private static String DB_PASSWORD ="Dinamo1982";

    private static Connection connection() throws SQLException,ClassNotFoundException{
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/sportteam");
        dataSource.setUser("root");
        dataSource.setPassword("Dinamo1982");

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(dataSource.getURL(),
                dataSource.getUser(),"Dinamo1982");
        conn.setAutoCommit(true);
        return conn;
    }

}


