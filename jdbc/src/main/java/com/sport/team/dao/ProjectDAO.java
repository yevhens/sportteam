package com.sport.team.dao;

import com.sport.team.entity.Project;
import com.sport.team.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO {

    /** сохраняет объект в базе данных */
    void add(Project project) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Project get(int id) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Project project) throws SQLException;

    /** Удаляет объект  */
    public void delete(Project project) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Project> getAll() throws SQLException;


}




