package com.sport.team.service;
import com.sport.team.dao.CommunityDAO;
import com.sport.team.entity.Community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Communities_Service implements CommunityDAO {

    Connection connection;

    @Override
    public void add(Community community) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Community VALUES(?,?,?)");
            stmt.setInt(1,community.getId());
            stmt.setString(2,community.getName());
            stmt.setInt(3,community.getCreator().getId());
            stmt.executeUpdate();
            System.out.println("Successfully added community" + " " + community.getId());
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
    public Community get(int id) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Community community=new Community();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Community.id,Community.name FROM Communities WHERE Communities.id=?");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            rs.next();
            community.setId(rs.getInt(1));
            community.setName(rs.getString(2));
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
        return community;
    }

    @Override
    public void update(Community community) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
        stmt=connection.prepareStatement("UPDATE Communities SET Communities.name=? WHERE Communities.id=?");
        stmt.setString(1,community.getName());
        stmt.executeUpdate();
            System.out.println("Community with id"+" "+community.getId()+" "+ "is updated");


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
    public void delete(Community community) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Communities WHERE Community.id=?");
            stmt.setInt(1,community.getId());
            stmt.executeUpdate();
            System.out.println("Community with id"+" "+community.getId()+" "+ "is deleted");
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
    public List<Community> getAll() throws SQLException {
        List<Community> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Communities ORDER BY id");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Community community=new Community();
                community.setId(rs.getInt(1));
                community.setName(rs.getString(2));
                list.add(community);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}
