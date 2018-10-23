package com.sport.team.service;

import com.sport.team.dao.DonationDAO;
import com.sport.team.entity.Donation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Donation_Service implements DonationDAO {

    Connection connection;

    @Override
    public void add(Donation donation) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Donations VALUES(?,?,?,?,?)");
            stmt.setInt(1,donation.getId());
            stmt.setDouble(2,donation.getAmount());
            stmt.setDate(3,donation.getDateAdded());
            stmt.setInt(4,donation.getProject().getId());
            stmt.setInt(5,donation.getUser().getId());
            stmt.executeUpdate();
            System.out.println("Successfully added donation" + " " + donation.getId());
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
    public Donation get(int id) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Donation donation=new Donation();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Donations.id,Donations.amount,Donations.date FROM Donations WHERE Donations.id=?");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            rs.next();
            donation.setId(rs.getInt(1));
            donation.setAmount(rs.getDouble(2));
            donation.setDateAdded(rs.getDate(3));

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
        return donation;
    }

    @Override
    public void update(Donation donation) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
        stmt=connection.prepareStatement("UPDATE Donations SET Donations.amount=?, Donations.date=? WHERE Donations.id=?");
        stmt.setDouble(1,donation.getAmount());
        stmt.setDate(2,donation.getDateAdded());
        stmt.setInt(3,donation.getId());
        stmt.executeUpdate();
            System.out.println("Donation with id"+" "+donation.getId()+" "+ "is updated");


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
    public void delete(Donation donation) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Donations WHERE Donation.id=?");
            stmt.setInt(1,donation.getId());
            stmt.executeUpdate();
            System.out.println("Donation with id"+" "+donation.getId()+" "+ "is deleted");
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
    public List<Donation> getAll() throws SQLException {
        List<Donation> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Donations ORDER BY id");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Donation donation=new Donation();
                donation.setId(rs.getInt(1));
                donation.setAmount(rs.getDouble(2));
                donation.setDateAdded(rs.getDate(3));
                list.add(donation);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}
