package com.sport.team.dao;

import com.sport.team.entity.Project_Volunteers;
import com.sport.team.entity.Users_Skills;

import java.sql.SQLException;
import java.util.List;

public interface Project_Volunteers_DAO {

    /** сохраняет объект в базе данных */
    void add(Project_Volunteers project_volunteers) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Project_Volunteers get(int projectId, int userId) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Project_Volunteers project_volunteers) throws SQLException;

    /** Удаляет объект  */
    public void delete(Project_Volunteers project_volunteers) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Project_Volunteers> getAll() throws SQLException;


}




