package com.sport.team.service;

import com.sport.team.dao.ToolDAO;
import com.sport.team.dao.Users_Tools_DAO;
import com.sport.team.entity.Tool;
import com.sport.team.entity.Users_Skills;
import com.sport.team.entity.Users_Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Users_Tools_Service implements Users_Tools_DAO {

    Connection connection;

    @Override
    public void add(Users_Tools users_Tools) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Users_Tools VALUES(?,?)");
            stmt.setInt(1,users_Tools.getUserId());
            stmt.setInt(2,users_Tools.getToolId());
            stmt.executeUpdate();
            System.out.println("Successfully added user_id " + " " + users_Tools.getUserId());
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
    public Users_Tools get(int userId,int skillId) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Users_Tools users_Tools=new Users_Tools();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Users_Tools.userId,Users_Tools.skillId FROM Users_Tools WHERE Users_Tools.userId.id=? AND Users_Tools.skillId=?");
            stmt.setInt(1,userId);
            stmt.setInt(2,skillId);
            rs=stmt.executeQuery();
            rs.next();
            users_Tools.setUserId((rs.getInt(1)));
            users_Tools.setToolId((rs.getInt(2)));
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
        return users_Tools;
    }

    @Override
    public void update(Users_Tools users_Tools) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("UPDATE Users_Tools SET Users_Tools.userId=? WHERE Users_Tools.skillId=?");
            stmt.setInt(1,users_Tools.getUserId());
            stmt.setInt(2,users_Tools.getToolId());
            stmt.executeUpdate();
            System.out.println("User with id"+" "+users_Tools.getUserId()+" "+ "is updated");
            System.out.println("Skill with id"+" "+users_Tools.getToolId()+" "+ "is updated");


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
    public void delete(Users_Tools users_Tools) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Users_Tools WHERE Users_Tools.userId=? AND Users_Tools.ToolsId=?");
            stmt.setInt(1,users_Tools.getUserId());
            stmt.setInt(2,users_Tools.getToolId());
            stmt.executeUpdate();
            System.out.println("User with id"+" "+users_Tools.getUserId()+" "+ "is deleted");
            System.out.println("Skill with id"+" "+users_Tools.getToolId()+" "+ "is deleted");
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
    public List<Users_Tools> getAll() throws SQLException {
        List<Users_Tools> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Users_Tools ORDER BY Users_Tools.userId");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Users_Tools users_Tools=new Users_Tools();
                users_Tools.setUserId(rs.getInt(1));
                users_Tools.setToolId(rs.getInt(2));
                list.add(users_Tools);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}

