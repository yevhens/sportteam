package com.sport.team.service;

import com.sport.team.Util;
import com.sport.team.dao.ToolDAO;
import com.sport.team.entity.Tool;
import com.sport.team.entity.Tool;
import com.sport.team.entity.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Tool_Service implements ToolDAO {

    Connection connection;

    @Override
    public void add(Tool Tool) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Tools VALUES(?,?)");
            stmt.setInt(1,Tool.getId());
            stmt.setString(2,Tool.getName());
            stmt.executeUpdate();
            System.out.println("Successfully added Tool" + " " + Tool.getName());
            stmt.close();
        }catch (Exception e){e.printStackTrace();

        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(connection !=null){
                connection.close();
                System.out.println("Connection closed");
            }
        }
    }

    @Override
    public Tool get(int id) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Tool Tool=new Tool();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Tools.id,Tools.name FROM Tools WHERE Tools.id=?");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            rs.next();
            Tool.setId(rs.getInt(1));
            Tool.setName(rs.getString(2));
            stmt.close();

        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(rs !=null){
                rs.close();
            }
            if(connection !=null){
                connection.close();
                System.out.println("Connection closed");
            }


        }
        return Tool;
    }

    @Override
    public void update(Tool Tool) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("UPDATE Tools SET Tools.name=? WHERE Tools.id=?");
            stmt.setString(1,Tool.getName());
            stmt.setInt(2,Tool.getId());
            stmt.executeUpdate();
            System.out.println("Tool with id"+" "+Tool.getId()+" "+ "is updated");


        }catch (Exception e){e.printStackTrace();
        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(connection !=null){
                connection.close();
                System.out.println("Connection closed");
            }
        }



    }
    @Override
    public void delete(Tool Tool) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Tools WHERE Tools.id=?");
            stmt.setInt(1,Tool.getId());
            stmt.executeUpdate();
            System.out.println("Tool with id"+" "+Tool.getId()+" "+ "is deleted");
        }catch (Exception e){e.printStackTrace();

        }finally {
            if(stmt !=null){
                stmt.close();
            }
            if(connection !=null){
                connection.close();
                System.out.println("Connection closed");
            }
        }


    }

    @Override
    public List<Tool> getAll() throws SQLException {
        List<Tool> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Tools ORDER BY id");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Tool Tool=new Tool();
                Tool.setId(rs.getInt(1));
                Tool.setName(rs.getString(2));
                list.add(Tool);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}
