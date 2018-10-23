package com.sport.team.service;

import com.sport.team.Util;
import com.sport.team.dao.UserDAO;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class User_Service implements UserDAO {

    Connection connection;

    @Override
    public void add(User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {
            connection=getconnection();
            preparedStatement = connection.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.executeUpdate();
            System.out.println("Profile successfully added");
            preparedStatement.close();

            for (Tool tool : user.getTools()) {
                preparedStatement = connection.prepareStatement("INSERT INTO Users_Tools VALUES(?,?)");
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, tool.getId());
                preparedStatement.executeUpdate();
                System.out.println("Tool successfully added");
                preparedStatement.close();
            }

            for (Skill skill : user.getSkills()) {
                preparedStatement = connection.prepareStatement("INSERT INTO Users_Skills VALUES(?,?)");
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, skill.getId());
                preparedStatement.executeUpdate();
                System.out.println("Skill successfully added");
                preparedStatement.close();
            }

        } catch (Exception e) {// тут ловит ошибку
            e.printStackTrace();


        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public User get(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection=getconnection();
            preparedStatement = connection.prepareStatement("SELECT Users.id,Users.name,Users.email,Users.phone FROM " +
                    "Users WHERE Users.id=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setPhone(rs.getString(4));
            rs.close();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement("SELECT Tools.id,Tools.name FROM Tools,Users_tools" +
                    "WHERE Users_Tools.userId=? AND Users_Tools.toolId=Tools.id");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tool tool = new Tool();
                tool.setId(rs.getInt(1));
                tool.setName(rs.getString(2));
                user.getTools().add(tool);
            }
            rs.close();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement("SELECT Skills.id,Skills.name FROM Skills,Users_skills" +
                    "WHERE Users_Skills.userId=? AND Users_Skills.toolId=Skills.id");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt(1));
                skill.setName(rs.getString(2));
                user.getSkills().add(skill);
            }
            return user;

        } catch (Exception e) { e.printStackTrace();
            return null;}
        finally {
            if(rs !=null){
                rs.close();
            }
            if(preparedStatement !=null){
                preparedStatement.close();
            }
        }

    }




    @Override
    public void update(User user) throws SQLException {

        PreparedStatement statement=null;


        try{
            connection=getconnection();
            statement=connection.prepareStatement("UPDATE Users SET Users.name=?,Users.email=?,Users.phone=? " +
                    "WHERE Users.id=?");
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPhone());
            statement.setInt(4,user.getId());
            statement.executeUpdate();
            System.out.println("User successfully updated");
            statement.close();

            for (Tool tool : user.getTools()){
                statement=connection.prepareStatement("UPDATE Users_Tools SET Users_Tools.toolID=? WHERE Users_Tools.userId=?");
                statement.setInt(1,tool.getId());
                statement.setInt(2,user.getId());
                statement.executeUpdate();
                System.out.println("Tools successfully updated");
                statement.close();
            }

            for (Skill skill : user.getSkills()){
                statement=connection.prepareStatement("UPDATE Users_Skills SET Users_Skill.skillID=? WHERE Users_Skills.userId=?");
                statement.setInt(1,skill.getId());
                statement.setInt(2,user.getId());
                statement.executeUpdate();
                System.out.println("Skills successfully updated");
                statement.close();
            }


        }catch (Exception e){e.printStackTrace();

            } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        PreparedStatement statement=null;
        try{
            connection=getconnection();
            statement=connection.prepareStatement("DELETE from Users WHERE Users.id=?");
            statement.setInt(1,user.getId());
            statement.executeUpdate();
            System.out.println("User with" + " "+ user.getId()+ " deleted");


        }catch (Exception e){e.printStackTrace();
        }finally {
            if(statement !=null){
                statement.close();
            }
            if(connection !=null){
                connection.close();
                System.out.println("Connection closed");
            }
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            connection=getconnection();
            statement=connection.prepareStatement("SELECT * FROM users ORDER BY id");
            rs=statement.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhone(rs.getString(4));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        }

        return users;

    }
}
