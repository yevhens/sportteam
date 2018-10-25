
package com.sport.team.service;

import com.sport.team.dao.Project_Volunteers_DAO;
import com.sport.team.entity.Project_Volunteers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Project_Volunteers_Service implements Project_Volunteers_DAO {

    Connection connection;

    @Override
    public void add(Project_Volunteers project_volunteers) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Project_Volunteers VALUES(?,?)");
            stmt.setInt(1,project_volunteers.getProjectId());
            stmt.setInt(2,project_volunteers.getUserId());
            stmt.executeUpdate();
            System.out.println("Successfully added project_id " + " " + project_volunteers.getProjectId());
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
    public Project_Volunteers get(int projectId,int userId) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Project_Volunteers project_volunteers=new Project_Volunteers();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Project_Volunteers.projectId,Project_Volunteers.userId FROM Project_Volunteers" +
                    " WHERE Project_Volunteers.projectId=? AND Project_Volunteers.userId=?");
            stmt.setInt(1,projectId);
            stmt.setInt(2,userId);
            rs=stmt.executeQuery();
            rs.next();
            project_volunteers.setProjectId((rs.getInt(1)));
            project_volunteers.setUserId((rs.getInt(2)));
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
        return project_volunteers;
    }

    @Override
    public void update(Project_Volunteers project_volunteers) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("UPDATE Project_Volunteers SET Project_Volunteers.projectId=?" +
                    " WHERE Project_Volunteers.userId=?");
            stmt.setInt(1,project_volunteers.getProjectId());
            stmt.setInt(2,project_volunteers.getUserId());
            stmt.executeUpdate();
            System.out.println("Project with id"+" "+project_volunteers.getProjectId()+" "+ "is updated");
            System.out.println("User with id"+" "+project_volunteers.getUserId()+" "+ "is updated");


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
    public void delete(Project_Volunteers project_volunteers) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Project_Volunteers WHERE Project_Volunteers.projectId=? AND Project_Volunteers.userId=?");
            stmt.setInt(1,project_volunteers.getProjectId());
            stmt.setInt(2,project_volunteers.getUserId());
            stmt.executeUpdate();
            System.out.println("Project with id"+" "+project_volunteers.getProjectId()+" "+ "is deleted");
            System.out.println("User with id"+" "+project_volunteers.getUserId()+" "+ "is deleted");
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
    public List<Project_Volunteers> getAll() throws SQLException {
        List<Project_Volunteers> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Project_Volunteers  ORDER BY Project_Volunteers.projectId");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Project_Volunteers project_volunteers=new Project_Volunteers();
                project_volunteers.setProjectId(rs.getInt(1));
                project_volunteers.setUserId(rs.getInt(2));
                list.add(project_volunteers);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}


