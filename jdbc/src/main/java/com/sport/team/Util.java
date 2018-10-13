package com.sport.team;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

public class Util {

    private static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/sportteam?useSSL=false";
    private static String DB_USERNAME = "root";
    private static String DB_PASSWORD = "Dinamo1982";


    private static void initDB() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getconnection();
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("CREATE TABLE Users (id INT PRIMARY KEY, name VARCHAR(255),"
                    + "email VARCHAR(255), phone VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Tools (id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Skills (id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Users_Tools (userId INT,toolID INT,"
                    + "PRIMARY KEY (userId,toolID))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Users_Skills (userId INT,skillID INT,"
                    + "PRIMARY KEY (userId,skillID))");
            stmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if(stmt !=null){
                stmt.close();
            }
            if(conn !=null){
                conn.close();
            }
        }
    }

    public static Connection getconnection() {
        Connection connection=null;
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL(DB_URL);
            dataSource.setUser(DB_USERNAME);
            dataSource.setPassword(DB_PASSWORD);
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dataSource.getURL(),
                    dataSource.getUser(), DB_PASSWORD);
            connection.setAutoCommit(true);
            System.out.println("Connection completed");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }

        return connection;
    }


}


