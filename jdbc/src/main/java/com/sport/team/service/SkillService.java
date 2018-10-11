package com.sport.team.service;

import com.sport.team.Util;
import com.sport.team.dao.SkillDAO;
import com.sport.team.entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SkillService extends Util implements SkillDAO {

    Connection connection=getconnection();
    @Override
    public void add(Skill skill) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Skills VALUES(?,?)");
            stmt.setInt(1,skill.getId());
            stmt.setString(2,skill.getName());
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
    public Skill get(int id) {
        return null;
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public void delete(Skill skill) {

    }

    @Override
    public List<Skill> getAll() throws SQLException {
        return null;
    }
}
