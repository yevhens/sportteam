package com.sport.team.dao;

import com.sport.team.entity.Project_Volunteers;
import com.sport.team.entity.Service_Events_Projects;

import java.sql.SQLException;
import java.util.List;

public interface Service_Event_Projects_DAO {

    /** сохраняет объект в базе данных */
    void add(Service_Events_Projects service_events_projects) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Service_Events_Projects get(int service_eventId, int projectId) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Service_Events_Projects service_events_projects) throws SQLException;

    /** Удаляет объект  */
    public void delete(Service_Events_Projects service_events_projects) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Service_Events_Projects> getAll() throws SQLException;


}




