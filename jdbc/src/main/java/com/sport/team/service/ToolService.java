package com.sport.team.service;

import com.sport.team.Util;
import com.sport.team.dao.ToolDAO;
import com.sport.team.entity.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ToolService extends Util implements ToolDAO {

    Connection connection=getconnection();

    @Override
    public void add(Tool tool) throws SQLException {

        PreparedStatement stmt=null;

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Tools VALUES(?,?)");
            stmt.setInt(1,tool.getId());
            stmt.setString(2,tool.getName());
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){e.printStackTrace();
            if (connection !=null){
                connection.rollback();
            }
        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(connection !=null){
                connection.close();
            }
        }
    }

    @Override
    public Tool get(int id) {
        return null;
    }

    @Override
    public void update(Tool tool) {

    }

    @Override
    public void delete(Tool tool) {

    }

    @Override
    public List<Tool> getAll() throws SQLException {
        return null;
    }
}
