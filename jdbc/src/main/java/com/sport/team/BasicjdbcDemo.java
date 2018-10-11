package com.sport.team;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasicjdbcDemo {


    private static void initDB() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connection();
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


    private static void insertUser(User user) throws SQLException{

        Connection conn=null;
        PreparedStatement stmt=null;


        try{
            conn=connection();
            stmt=conn.prepareStatement("INSERT INTO Users VALUES(?,?,?,?)");
            stmt.setInt(1,user.getId());
            stmt.setString(2,user.getName());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPhone());
            stmt.executeUpdate();
            stmt.close();

            for(Tool tool : user.getTools()){
                stmt=conn.prepareStatement("INSERT INTO Users_Tools VALUES(?,?)");
                stmt.setInt(1,user.getId());
                stmt.setInt(2,tool.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for(Skill skill : user.getSkills()){
                stmt=conn.prepareStatement("INSERT INTO Users_Skills VALUES(?,?)");
                stmt.setInt(1,user.getId());
                stmt.setInt(2,skill.getId());
                stmt.executeUpdate();
                stmt.close();
            }


        }catch (Exception e){e.printStackTrace();
        if (conn !=null){
            conn.rollback();
        }

        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(conn !=null){
                conn.close();
            }
        }

    }
    private static void insertTool(Tool tool) throws SQLException{

        Connection conn=null;
        PreparedStatement stmt=null;

        try{
            conn=connection();
            stmt=conn.prepareStatement("INSERT INTO Tools VALUES(?,?)");
            stmt.setInt(1,tool.getId());
            stmt.setString(2,tool.getName());
            stmt.executeUpdate();
            stmt.close();

        }catch (Exception e){e.printStackTrace();
            if (conn !=null){
                conn.rollback();
            }

        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(conn !=null){
                conn.close();
            }
        }

    }

    private static void insertSkill(Skill skill) throws SQLException{

        Connection conn=null;
        PreparedStatement stmt=null;

        try{
            conn=connection();
            stmt=conn.prepareStatement("INSERT INTO Skills VALUES(?,?)");
            stmt.setInt(1,skill.getId());
            stmt.setString(2,skill.getName());
            stmt.executeUpdate();
            stmt.close();

        }catch (Exception e){e.printStackTrace();
            if (conn !=null){
                conn.rollback();
            }

        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(conn !=null){
                conn.close();
            }
        }

    }

    private static User getUser(int id) throws SQLException{


        PreparedStatement stmt=null;
        ResultSet rs=null;

        try (Connection conn=connection()){
            stmt=conn.prepareStatement("SELECT Users.id,Users.name,Users.email,Users.phone FROM Users WHERE Users.id=?");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            rs.next();

            User user=new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setPhone(rs.getString(4));
            rs.close();
            stmt.close();

            user.setTools(new ArrayList<Tool>());
            user.setSkills(new ArrayList<Skill>());

            stmt=conn.prepareStatement("SELECT Tools.id,Tools.name FROM Tools, Users_Tools"
                    + "WHERE Users_Tools.userId=? AND Users_Tools.toolId=Tools.id");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            while(rs.next()){
                Tool tool=new Tool();
                tool.setId(rs.getInt(1));
                tool.setName(rs.getString(2));
                user.getTools().add(tool);
            }
            rs.close();
            stmt.close();

            stmt=conn.prepareStatement("SELECT Skills.id,Skills.name FROM Skills, Users_Skills"
                    + "WHERE Users_Skills.userId=? AND Users_Skills.skillId=Skills.id");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            while(rs.next()){
                Skill skill=new Skill();
                skill.setId(rs.getInt(1));
                skill.setName(rs.getString(2));
                user.getSkills().add(skill);
            }
            return user;

        }catch (Exception e){e.printStackTrace();
        return null;
        }finally {
            if(rs !=null){
                rs.close();
            }
            if(stmt !=null){
                stmt.close();
            }
        }


    }

    public static void main(String[] args) {

        try{
            initDB();
            Tool tool=new Tool();
            tool.setId(1);
            tool.setName("Hammer");
            insertTool(tool);
            List<Tool> tools=new ArrayList<>();
            tools.add(tool);

            Skill skill=new Skill();
            skill.setId(1);
            skill.setName("Hammering things");
            insertSkill(skill);
            List<Skill> skills=new ArrayList<>();
            skills.add(skill);

            User user=new User();
            user.setId(1);
            user.setName("Brett Meyer");
            user.setEmail("mayer@gmail.com");
            user.setPhone("044-516-19-99");
            user.setTools(tools);
            user.setSkills(skills);

            insertUser(user);
            user=getUser(1);
            System.out.println(user.toString());
        }catch (Exception e)
        {e.printStackTrace();}

    }

}




