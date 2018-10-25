package com.sport.team.service;

import com.sport.team.dao.ProjectDAO;

import com.sport.team.entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Project_Service implements ProjectDAO {

    Connection connection;

    @Override
    public void add(Project project) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {
            connection=getconnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Projects VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setInt(2,project.getOrganizer().getId());
            preparedStatement.setString(3, project.getAddress1());
            preparedStatement.setString(4, project.getAddress2());
            preparedStatement.setString(5, project.getCity());
            preparedStatement.setDate(6, project.getDateAdded());
            preparedStatement.setString(7, project.getDescription());
            preparedStatement.setString(8, project.getEmail());
            preparedStatement.setString(9, project.getFirstName());
            preparedStatement.setString(10, project.getLastName());
            preparedStatement.setString(11, project.getPhone());
            preparedStatement.setString(12, project.getState());
            preparedStatement.setInt(13,project.getSubmitter().getId());
            preparedStatement.setString(14, project.getZip());
            preparedStatement.setString(15, project.getTitle());

            preparedStatement.executeUpdate();
            System.out.println("Project successfully added");
            preparedStatement.close();

            for(String image_url : project.getImageUrls()){
                preparedStatement = connection.prepareStatement("INSERT INTO Project_ImageURLs VALUES(?,?)");
                preparedStatement.setInt(1,project.getId());
                preparedStatement.setString(2,image_url);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

            for(User user : project.getVolunteers()){
                preparedStatement = connection.prepareStatement("INSERT INTO Project_Volunteers VALUES(?,?)");
                preparedStatement.setInt(1,project.getId());
                preparedStatement.setInt(2,user.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }


            for (Comment comment : project.getComments()) {
                preparedStatement = connection.prepareStatement("INSERT INTO Comments VALUES(?,?,?,?,?)");
                preparedStatement.setInt(1, comment.getId());
                preparedStatement.setDate(2, comment.getDateAdded());
                preparedStatement.setInt(3, comment.getSubmitter().getId());
                preparedStatement.setString(4, comment.getText());
                preparedStatement.setInt(5, comment.getProject().getId());
                preparedStatement.executeUpdate();
                System.out.println("Comment successfully added");
                preparedStatement.close();
            }

            for (Donation donation : project.getDonations()) {
                preparedStatement = connection.prepareStatement("INSERT INTO Donations VALUES(?,?,?,?,?)");
                preparedStatement.setInt(1, donation.getId());
                preparedStatement.setDouble(2, donation.getAmount());
                preparedStatement.setDate(3, donation.getDateAdded());
                preparedStatement.setInt(4, donation.getProject().getId());
                preparedStatement.setInt(4, donation.getUser().getId());
                preparedStatement.executeUpdate();
                System.out.println("Donation successfully added");
                preparedStatement.close();
            }

        } catch (Exception e) {// тут ловит ошибку
            e.printStackTrace();


        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Project get(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection=getconnection();
            preparedStatement = connection.prepareStatement("SELECT Projects.id,Projects.adress1," +
                    "Projects.address2,Projects.city,Projects.date,Projects.description,Projects.email,Projects.firstname," +
                    "Projects.lastname,Projects.phone,Projects.state,Projects.zip,Projects.title FROM " +
                    "Projects WHERE Projects.id=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();
            Project project = new Project();
            project.setId(rs.getInt(1));
            project.setAddress1(rs.getString(2));
            project.setAddress2(rs.getString(3));
            project.setCity(rs.getString(4));
            project.setDateAdded(rs.getDate(5));
            project.setDescription(rs.getString(6));
            project.setEmail(rs.getString(7));
            project.setFirstName(rs.getString(8));
            project.setLastName(rs.getString(9));
            project.setPhone(rs.getString(10));
            project.setState(rs.getString(11));
            project.setZip(rs.getString(12));
            project.setTitle(rs.getString(13));
            rs.close();

            preparedStatement.close();
            preparedStatement = connection.prepareStatement("SELECT Comments.id,Comments.date,Comments.text FROM Comments" +
                    "WHERE Comments.projectId=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setDateAdded(rs.getDate(2));
                comment.setText(rs.getString(3));
                project.getComments().add(comment);
            }
            rs.close();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement("SELECT Donations.id,Donations.amount,Donations.date FROM Donations" +
                    "WHERE Donations.projectId=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Donation donation = new Donation();
                donation.setId(rs.getInt(1));
                donation.setAmount(rs.getDouble(2));
                donation.setDateAdded(rs.getDate(3));
                project.getDonations().add(donation);
            }
            return project;

        } catch (Exception e) { e.printStackTrace();
            return null;}
        finally {
            if(rs !=null){
                rs.close();
            }
            if(preparedStatement !=null){
                preparedStatement.close();
            }
        }

    }


    @Override
    public void update(Project project) throws SQLException {

        PreparedStatement statement=null;


        try{
            connection=getconnection();
            statement=connection.prepareStatement("UPDATE Projects SET Projects.adress1=?," +
                    "Projects.address2=?,Projects.city=?,Projects.date=?,Projects.description=?,Projects.email=?,Projects.firstname=?," +
                    "Projects.lastname=?,Projects.phone=?,Projects.state=?,Projects.zip=?,Projects.title=?" +
                    "WHERE Projects.id=?");
            statement.setString(1,project.getAddress1());
            statement.setString(2,project.getAddress2());
            statement.setString(3,project.getCity());
            statement.setDate(4,project.getDateAdded());
            statement.setString(5,project.getDescription());
            statement.setString(6,project.getEmail());
            statement.setString(7,project.getFirstName());
            statement.setString(8,project.getLastName());
            statement.setString(9,project.getPhone());
            statement.setString(10,project.getState());
            statement.setString(11,project.getZip());
            statement.setString(12,project.getTitle());
            statement.setInt(13,project.getId());
            statement.executeUpdate();
            System.out.println("Project successfully updated");
            statement.close();

            for (Comment comment : project.getComments()){
                statement=connection.prepareStatement("UPDATE Comments SET Comments.id=?,Comments.date=?,Comments.text?" +
                        " WHERE Comments.projectId=?");
                statement.setInt(1,comment.getId());
                statement.setDate(2,comment.getDateAdded());
                statement.setString(3,comment.getText());
                statement.setInt(4,comment.getProject().getId());
                statement.executeUpdate();
                System.out.println("Comments successfully updated");
                statement.close();
            }

            for (Donation donation : project.getDonations()){
                statement=connection.prepareStatement("UPDATE Donations SET Donations.id=?,Donations.amount=?,Donations.date?"+
                        " WHERE Donations.projectId=?");
                statement.setInt(1,donation.getId());
                statement.setDouble(2,donation.getAmount());
                statement.setDate(3,donation.getDateAdded());
                statement.executeUpdate();
                System.out.println("Donations successfully updated");
                statement.close();
            }


        }catch (Exception e){e.printStackTrace();

            } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Project project) throws SQLException {
        PreparedStatement statement=null;
        try{
            connection=getconnection();
            statement=connection.prepareStatement("DELETE from Projects WHERE Projects.id=?");
            statement.setInt(1,project.getId());
            statement.executeUpdate();
            System.out.println("Project with" + " "+ project.getId()+ " deleted");

        }catch (Exception e){e.printStackTrace();
        }finally {
            if(statement !=null){
                statement.close();
            }
            if(connection !=null){
                connection.close();
                System.out.println("Connection closed");
            }
        }
    }

    @Override
    public List<Project> getAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Project> projects = new ArrayList<>();

        try {
            connection=getconnection();
            statement=connection.prepareStatement("SELECT * FROM users ORDER BY id");
            rs=statement.executeQuery();
            while(rs.next()){
                Project project = new Project();
                project.setId(rs.getInt(1));
                project.setAddress1(rs.getString(2));
                project.setAddress2(rs.getString(3));
                project.setCity(rs.getString(4));
                project.setDateAdded(rs.getDate(5));
                project.setDescription(rs.getString(6));
                project.setEmail(rs.getString(7));
                project.setFirstName(rs.getString(8));
                project.setLastName(rs.getString(9));
                project.setPhone(rs.getString(10));
                project.setState(rs.getString(11));
                project.setZip(rs.getString(12));
                project.setTitle(rs.getString(13));
                projects.add(project);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        }

        return projects;

    }
}

