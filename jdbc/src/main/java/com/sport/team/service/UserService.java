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
import java.util.List;

import static com.sport.team.Util.getconnection;

public class UserService implements UserDAO {

    Connection connection = getconnection();

    @Override
    public void add(User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {

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
                System.out.println("Tools successfully added");
                preparedStatement.close();
            }

            for (Skill skill : user.getSkills()) {
                preparedStatement = connection.prepareStatement("INSERT INTO Users_Skills VALUES(?,?)");
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, skill.getId());
                preparedStatement.executeUpdate();
                System.out.println("Skills successfully added");
                preparedStatement.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }

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
    public void update(User object) {

    }

    @Override
    public void delete(User object) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }


}
