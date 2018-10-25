package com.sport.team.service;

import com.sport.team.dao.ServiceEventDAO;
import com.sport.team.dao.UserDAO;
import com.sport.team.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Service_Event_Service implements ServiceEventDAO {

    Connection connection;

    @Override
    public void add(ServiceEvent serviceEvent) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {
            connection=getconnection();
            preparedStatement = connection.prepareStatement("INSERT INTO ServiceEvents VALUES(?,?,?,?,?,?)");
            preparedStatement.setInt(1, serviceEvent.getId());
            preparedStatement.setInt(2, serviceEvent.getOrganizer().getId());
            preparedStatement.setInt(3, serviceEvent.getCommunity().getId());
            preparedStatement.setString(4, serviceEvent.getDescription());
            preparedStatement.setDate(5, serviceEvent.getDate());
            preparedStatement.setString(6, serviceEvent.getName());
            preparedStatement.executeUpdate();
            System.out.println("ServiceEvent successfully added");
            preparedStatement.close();


            for(Project project : serviceEvent.getProjects()){
                preparedStatement=connection.prepareStatement("INSERT INTO Service_Event_Projects VALUES(?,?)");
                preparedStatement.setInt(1,serviceEvent.getId());
                preparedStatement.setInt(2,project.getId());
                preparedStatement.executeUpdate();
                System.out.println("Service_Event_Projects successfully added");
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
    public ServiceEvent get(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection=getconnection();
            preparedStatement = connection.prepareStatement("SELECT ServiceEvents.id,ServiceEvents.description," +
                    "ServiceEvents.date, ServiceEvents.name FROM " +
                    "ServiceEvents WHERE ServiceEvents.id=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();
            ServiceEvent serviceEvent = new ServiceEvent();
            serviceEvent.setId(rs.getInt(1));
            serviceEvent.setName(rs.getString(2));
            serviceEvent.setDate(rs.getDate(3));
            serviceEvent.setName(rs.getString(4));
            rs.close();
            preparedStatement.close();
            return serviceEvent;

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
    public void update(ServiceEvent serviceEvent) throws SQLException {

        PreparedStatement statement=null;


        try{
            connection=getconnection();
            statement=connection.prepareStatement("UPDATE ServiceEvents SET ServiceEvents.description=?,ServiceEvents.date=?," +
                    "ServiceEvents.name=? " +
                    "WHERE ServiceEvents.id=?");
            statement.setString(1,serviceEvent.getDescription());
            statement.setDate(2,serviceEvent.getDate());
            statement.setString(3,serviceEvent.getName());
            statement.setInt(4,serviceEvent.getId());
            statement.executeUpdate();
            System.out.println("Service Event successfully updated");
            statement.close();




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
    public void delete(ServiceEvent serviceEvent) throws SQLException {
        PreparedStatement statement=null;
        try{
            connection=getconnection();
            statement=connection.prepareStatement("DELETE from ServiceEvents WHERE ServiceEvents.id=?");
            statement.setInt(1,serviceEvent.getId());
            statement.executeUpdate();
            System.out.println("ServiceEvent with" + " "+ serviceEvent.getId()+ " deleted");


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
    public List<ServiceEvent> getAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<ServiceEvent> serviceEvents = new ArrayList<>();

        try {
            connection=getconnection();
            statement=connection.prepareStatement("SELECT * FROM ServiceEvents ORDER BY id");
            rs=statement.executeQuery();
            while(rs.next()){
                ServiceEvent serviceEvent=new ServiceEvent();
                serviceEvent.setId(rs.getInt(1));
                serviceEvent.setName(rs.getString(2));
                serviceEvent.setDate(rs.getDate(3));
                serviceEvent.setName(rs.getString(4));
                serviceEvents.add(serviceEvent);
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

        return serviceEvents;

    }
}
