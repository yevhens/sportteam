
package com.sport.team.service;

import com.sport.team.dao.Service_Event_Projects_DAO;
import com.sport.team.entity.Service_Events_Projects;
import com.sport.team.entity.Users_Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sport.team.Util.getconnection;

public class Service_Event_Projects_Service implements Service_Event_Projects_DAO {

    Connection connection;

    @Override
    public void add(Service_Events_Projects service_events_projects) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("INSERT INTO Service_Events_Projects VALUES(?,?)");
            stmt.setInt(1,service_events_projects.getService_eventId());
            stmt.setInt(2,service_events_projects.getProjectId());
            stmt.executeUpdate();
            System.out.println("Successfully added service_eventiD with id " + " " + service_events_projects.getService_eventId());
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
    public Service_Events_Projects get(int service_eventId,int projectId) throws SQLException {

        PreparedStatement stmt=null;
        ResultSet rs=null;
        Service_Events_Projects service_events_projects=new Service_Events_Projects();

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("SELECT Service_Events_Projects.service_eventId,Service_Events_Projects.projectId " +
                    "FROM Service_Events_Projects WHERE Service_Events_Projects.service_eventId=? AND Service_Events_Projects.projectId=?");
            stmt.setInt(1,service_eventId);
            stmt.setInt(2,projectId);
            rs=stmt.executeQuery();
            rs.next();
            service_events_projects.setService_eventId((rs.getInt(1)));
            service_events_projects.setProjectId((rs.getInt(2)));
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
        return service_events_projects;
    }

    @Override
    public void update(Service_Events_Projects service_events_projects) throws SQLException {
        PreparedStatement stmt=null;

        try{
            connection=getconnection();
            stmt=connection.prepareStatement("UPDATE Service_Event_Projects SET Service_Event_Projects.projectId=? " +
                    "WHERE Service_Event_Projects.service_eventId=?");
            stmt.setInt(1,service_events_projects.getProjectId());
            stmt.setInt(2,service_events_projects.getService_eventId());
            stmt.executeUpdate();
            System.out.println("Service_Event with id"+" "+service_events_projects.getProjectId()+" "+ "is updated");


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
    public void delete(Service_Events_Projects service_events_projects) throws SQLException {
        PreparedStatement stmt=null;
        try{
            connection=getconnection();
            stmt=connection.prepareStatement("DELETE FROM Service_Event_Projects WHERE Service_Event_Projects.service_eventId=? " +
                    "AND Service_Event_Projects.projectId=?");
            stmt.setInt(1,service_events_projects.getService_eventId());
            stmt.setInt(2,service_events_projects.getProjectId());
            stmt.executeUpdate();
            System.out.println("Service_Event with id"+" "+service_events_projects.getService_eventId()+" "+ "is deleted");

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
    public List<Service_Events_Projects> getAll() throws SQLException {
        List<Service_Events_Projects> list=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            connection=getconnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM Service_Event_Projects ORDER BY Service_Event_Projects.service_eventId");
            rs=preparedStatement.executeQuery();
            while(rs.next()){
                Service_Events_Projects service_events_projects=new Service_Events_Projects();
                service_events_projects.setService_eventId(rs.getInt(1));
                service_events_projects.setProjectId(rs.getInt(2));
                list.add(service_events_projects);
            }

        }catch (Exception e){e.printStackTrace();}

        if(connection !=null){
            connection.close();
            System.out.println("Connection closed");
        }


        return list;
    }
}


