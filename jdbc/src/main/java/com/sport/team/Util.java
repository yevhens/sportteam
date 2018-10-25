package com.sport.team;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

public class Util {

    private static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/sportteam?useSSL=false";
    private static String DB_USERNAME = "root";
    private static String DB_PASSWORD = "Dinamo1982";


    public static void initDB() throws SQLException {
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
                    + "PRIMARY KEY (userId,toolID),FOREIGN KEY (userId) REFERENCES Users(id),FOREIGN KEY (toolId) REFERENCES Tools(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Users_Skills (userId INT,skillId INT,"
                    + "PRIMARY KEY (userId,skillId),FOREIGN KEY (userId) REFERENCES Users(id),FOREIGN KEY (skillId) REFERENCES Skills(id))");
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

    public static void initDB2() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getconnection();
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            stmt=conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            stmt.executeUpdate();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Projects");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE Projects (id INT PRIMARY KEY,organizerId INT,"
                    +"adress1 VARCHAR(255), address2 VARCHAR(255),city VARCHAR(255),date DATE,description VARCHAR(255),email VARCHAR(255),"
                    +"firstname VARCHAR(255),lastname VARCHAR(255),phone VARCHAR(255),state VARCHAR(255),submitterId INT,"
                    + "zip VARCHAR(255),title VARCHAR(255),FOREIGN KEY (organizerId) REFERENCES Users(id),"
                    + "FOREIGN KEY (submitterId) REFERENCES Users(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Comments");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE Comments (id INT PRIMARY KEY, date DATE,submitterId INT,text VARCHAR(255),projectId INT,"
                    +"FOREIGN KEY (submitterId) REFERENCES Users(id),FOREIGN KEY (projectId) REFERENCES Projects(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Donations");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE Donations (id INT PRIMARY KEY, amount DOUBLE,date DATE,projectId INT,userId INT," +
                    "FOREIGN KEY (projectId) REFERENCES Projects(id),FOREIGN KEY (userId) REFERENCES Projects(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Communities");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE Communities (id INT PRIMARY KEY, text VARCHAR(255),creatorId INT," +
                    "FOREIGN KEY (creatorId) REFERENCES Users(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS ServiceEvents");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE ServiceEvents (id INT PRIMARY KEY,organizerId INT, " +
                    "communityId INT REFERENCES Communities(id),description VARCHAR(255),name VARCHAR(255)," +
                    "date DATE, FOREIGN KEY (organizerId) REFERENCES Users(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Project_Volunteers");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE Project_Volunteers (projectId INT,userID INT,"
                    + "PRIMARY KEY (projectId,userID),FOREIGN KEY (projectId) REFERENCES Projects(id),FOREIGN KEY (userId) REFERENCES Users(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Image_URLs");
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Project_ImageURLs");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE Project_ImageURLs (projectId INT,imageURL VARCHAR(255),"
                    + "PRIMARY KEY (projectId),FOREIGN KEY (projectId) REFERENCES Projects(id))");
            stmt.executeUpdate();
            stmt.close();
            stmt=conn.prepareStatement("DROP TABLE IF EXISTS Service_Event_Projects");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("CREATE TABLE Service_Event_Projects (service_eventId INT,projectId INT,"
                    + "PRIMARY KEY (service_eventId, projectId),FOREIGN KEY (service_eventId) REFERENCES ServiceEvents(id)" +
                    ",FOREIGN KEY (projectId) REFERENCES Projects(id))");
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

    public static void dropTables() throws SQLException{

        PreparedStatement preparedStatement=null;
        Connection connection=null;

        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("DROP TABLE Users,Tools,Skills,Users_Tools,Users_Skills");
            preparedStatement.executeUpdate();

        }catch (Exception e){e.printStackTrace();
        }finally {
            if(preparedStatement !=null){
                preparedStatement.close();
            }
            if(connection !=null){
                connection.close();
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


