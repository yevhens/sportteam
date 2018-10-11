package com.sport.team.service;

import com.sport.team.Util;
import com.sport.team.dao.UserDAO;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserService extends Util implements UserDAO {

    Connection connection=getconnection();

    @Override
    public void add(User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            for(Tool tool : user.getTools()){
                preparedStatement=connection.prepareStatement("INSERT INTO Users_Tools VALUES(?,?)");
                preparedStatement.setInt(1,user.getId());
                preparedStatement.setInt(2,tool.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

            for(Skill skill : user.getSkills()){
                preparedStatement=connection.prepareStatement("INSERT INTO Users_Skills VALUES(?,?)");
                preparedStatement.setInt(1,user.getId());
                preparedStatement.setInt(2,skill.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

        }catch (Exception e){e.printStackTrace();
            if (connection !=null){
                connection.rollback();
            }

        }finally {
            if( preparedStatement!=null){
                preparedStatement.close();
            }
            if(connection !=null){
                connection.close();
            }
        }
    }

    @Override
    public User get(int id) {
        return null;
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
