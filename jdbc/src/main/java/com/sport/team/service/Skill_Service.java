package com.sport.team.service;

import com.sport.team.Util;
import com.sport.team.dao.SkillDAO;
import com.sport.team.entity.Skill;
import com.sport.team.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Skill_Service implements SkillDAO {

    Connection connection;

    @Override
    public void add(Skill skill) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Skills VALUES(?,?)");
            stmt.setInt(1,skill.getId());
            stmt.setString(2,skill.getName());
            stmt.executeUpdate();
            System.out.println("Successfully added skill" + " " + skill.getName());
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
    public Skill get(int id) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Skill skill=new Skill();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Skills.id,Skills.name FROM Skills WHERE Skills.id=?");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            rs.next();
            skill.setId(rs.getInt(1));
            skill.setName(rs.getString(2));
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
        return skill;
    }

    @Override
    public void update(Skill skill) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
        stmt=connection.prepareStatement("UPDATE Skills SET Skills.name=? WHERE Skills.id=?");
        stmt.setString(1,skill.getName());
        stmt.setInt(2,skill.getId());
        stmt.executeUpdate();
            System.out.println("Skill with id"+" "+skill.getId()+" "+ "is updated");


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
    public void delete(Skill skill) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Skills WHERE Skills.id=?");
            stmt.setInt(1,skill.getId());
            stmt.executeUpdate();
            System.out.println("Skill with id"+" "+skill.getId()+" "+ "is deleted");
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
    public List<Skill> getAll() throws SQLException {
        List<Skill> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Skills ORDER BY id");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Skill skill=new Skill();
                skill.setId(rs.getInt(1));
                skill.setName(rs.getString(2));
                list.add(skill);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}
