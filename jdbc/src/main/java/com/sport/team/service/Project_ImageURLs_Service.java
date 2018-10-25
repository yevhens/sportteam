

package com.sport.team.service;

import com.sport.team.dao.Project_ImageURLs_DAO;
import com.sport.team.entity.Project_ImageURLs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Project_ImageURLs_Service implements Project_ImageURLs_DAO {

    Connection connection;

    @Override
    public void add(Project_ImageURLs project_ImageURL) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Project_ImageURLs VALUES(?,?)");
            stmt.setInt(1,project_ImageURL.getProjectId());
            stmt.setString(2,project_ImageURL.getImageURL());
            stmt.executeUpdate();
            System.out.println("Successfully added image_URL " + " " + project_ImageURL.getImageURL());
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
    public Project_ImageURLs get(int projectId,String imageURL) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Project_ImageURLs project_ImageURLs=new Project_ImageURLs();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Project_ImageURLs.projectId,Project_ImageURLs.userId FROM Project_ImageURLs" +
                    " WHERE Project_ImageURLs.projectId=? AND Project_ImageURLs.imageURL=?");
            stmt.setInt(1,projectId);
            stmt.setString(2,imageURL);
            rs=stmt.executeQuery();
            rs.next();
            project_ImageURLs.setProjectId((rs.getInt(1)));
            project_ImageURLs.setImageURL((rs.getString(2)));
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
        return project_ImageURLs;
    }

    @Override
    public void update(Project_ImageURLs project_ImageURLs) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("UPDATE Project_ImageURLs SET Project_ImageURLs.imageURL=?" +
                    " WHERE Project_ImageURLs.projectId=?");
            stmt.setString(1,project_ImageURLs.getImageURL());
            stmt.setInt(2,project_ImageURLs.getProjectId());
            stmt.executeUpdate();
            System.out.println("Project with id"+" "+project_ImageURLs.getProjectId()+" "+ "is updated");
            System.out.println("ImageURL "+" "+project_ImageURLs.getImageURL()+" "+ "is updated");


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
    public void delete(Project_ImageURLs project_ImageURLs) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Project_ImageURLs WHERE Project_ImageURLs.projectId=?");
            stmt.setInt(1,project_ImageURLs.getProjectId());
            stmt.executeUpdate();
            System.out.println("Project ImageURLs with id"+" "+project_ImageURLs.getProjectId()+" "+ "is deleted");
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
    public List<Project_ImageURLs> getAll() throws SQLException {
        List<Project_ImageURLs> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Project_ImageURLs  ORDER BY Project_ImageURLs.projectId");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Project_ImageURLs project_ImageURLs=new Project_ImageURLs();
                project_ImageURLs.setProjectId(rs.getInt(1));
                project_ImageURLs.setImageURL(rs.getString(2));
                list.add(project_ImageURLs);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}



