package com.sport.team.dao;

import com.sport.team.entity.Comment;
import com.sport.team.entity.Community;

import java.sql.SQLException;
import java.util.List;

public interface CommunityDAO {

    /** сохраняет объект в базе данных */
    void add(Community community) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Community get(int id) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Community community) throws SQLException;

    /** Удаляет объект  */
    public void delete(Community community) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Community> getAll() throws SQLException;


}




