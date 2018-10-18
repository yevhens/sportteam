package com.sport.team.service;

import com.sport.team.dao.SkillDAO;
import com.sport.team.dao.Users_Skills_DAO;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Users_Skills;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Users_Skills_Service implements Users_Skills_DAO {

    Connection connection;

    @Override
    public void add(Users_Skills users_skills) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Users_Skills VALUES(?,?)");
            stmt.setInt(1,users_skills.getUserId());
            stmt.setInt(2,users_skills.getSkillId());
            stmt.executeUpdate();
            System.out.println("Successfully added user_id " + " " + users_skills.getUserId());
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
    public Users_Skills get(int userId,int skillId) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Users_Skills users_skills=new Users_Skills();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Users_Skills.userId,Users_Skills.skillId FROM Users_Skills WHERE Users_Skills.userId=? AND Users_Skills.skillId=?");
            stmt.setInt(1,userId);
            stmt.setInt(2,skillId);
            rs=stmt.executeQuery();
            rs.next();
            users_skills.setUserId((rs.getInt(1)));
            users_skills.setSkillId((rs.getInt(2)));
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
        return users_skills;
    }

    @Override
    public void update(Users_Skills users_skills) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("UPDATE Users_Skills SET Users_Skills.userId=? WHERE Users_Skills.skillId=?");
            stmt.setInt(1,users_skills.getUserId());
            stmt.setInt(2,users_skills.getSkillId());
            stmt.executeUpdate();
            System.out.println("User with id"+" "+users_skills.getUserId()+" "+ "is updated");
            System.out.println("Skill with id"+" "+users_skills.getSkillId()+" "+ "is updated");


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
    public void delete(Users_Skills users_skills) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Users_Skills WHERE Users_Skills.userId=? AND Users_Skills.skillsId=?");
            stmt.setInt(1,users_skills.getUserId());
            stmt.setInt(2,users_skills.getSkillId());
            stmt.executeUpdate();
            System.out.println("User with id"+" "+users_skills.getUserId()+" "+ "is deleted");
            System.out.println("Skill with id"+" "+users_skills.getSkillId()+" "+ "is deleted");
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
    public List<Users_Skills> getAll() throws SQLException {
        List<Users_Skills> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Users_Skills ORDER BY Users_Skills.userId");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Users_Skills users_skills=new Users_Skills();
                users_skills.setUserId(rs.getInt(1));
                users_skills.setSkillId(rs.getInt(2));
                list.add(users_skills);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}

