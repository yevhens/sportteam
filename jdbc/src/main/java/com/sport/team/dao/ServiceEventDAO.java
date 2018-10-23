package com.sport.team.dao;

import com.sport.team.entity.Community;
import com.sport.team.entity.ServiceEvent;

import java.sql.SQLException;
import java.util.List;

public interface ServiceEventDAO {

    /** сохраняет объект в базе данных */
    void add(ServiceEvent serviceEvent) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public ServiceEvent get(int id) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(ServiceEvent serviceEvent) throws SQLException;

    /** Удаляет объект  */
    public void delete(ServiceEvent serviceEvent) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<ServiceEvent> getAll() throws SQLException;


}




