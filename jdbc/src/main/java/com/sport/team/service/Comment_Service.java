package com.sport.team.service;

import com.sport.team.dao.CommentDAO;
import com.sport.team.dao.SkillDAO;
import com.sport.team.entity.Comment;
import com.sport.team.entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Comment_Service implements CommentDAO {

    Connection connection;

    @Override
    public void add(Comment comment) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Comments VALUES(?,?,?,?,?)");
            stmt.setInt(1,comment.getId());
            stmt.setDate(2,comment.getDateAdded());
            stmt.setInt(3,comment.getSubmitter().getId());
            stmt.setString(4,comment.getText());
            stmt.setInt(5,comment.getProject().getId());
            stmt.executeUpdate();
            System.out.println("Successfully added comment" + " " + comment.getId());
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
    public Comment get(int id) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Comment comment=new Comment();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Comments.id,Comments.date,Comments.text FROM Comments WHERE Comments.id=?");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            rs.next();
            comment.setId(rs.getInt(1));
            comment.setDateAdded(rs.getDate(2));
            comment.setText(rs.getString(3));
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
        return comment;
    }

    @Override
    public void update(Comment comment) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
        stmt=connection.prepareStatement("UPDATE Comments SET Comments.date=?, Comments.text=? WHERE Comments.id=?");
        stmt.setDate(1,comment.getDateAdded());
        stmt.setString(2,comment.getText());
        stmt.setInt(3,comment.getId());
        stmt.executeUpdate();
            System.out.println("Comment with id"+" "+comment.getId()+" "+ "is updated");


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
    public void delete(Comment comment) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Comments WHERE Comment.id=?");
            stmt.setInt(1,comment.getId());
            stmt.executeUpdate();
            System.out.println("Comment with id"+" "+comment.getId()+" "+ "is deleted");
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
    public List<Comment> getAll() throws SQLException {
        List<Comment> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Comments ORDER BY id");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Comment comment=new Comment();
                comment.setId(rs.getInt(1));
                comment.setDateAdded(rs.getDate(2));
                comment.setText(rs.getString(3));
                list.add(comment);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}
