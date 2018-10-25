package com.sport.team.dao;

import com.sport.team.entity.Project_ImageURLs;

import java.sql.SQLException;
import java.util.List;

public interface Project_ImageURLs_DAO {

    /** сохраняет объект в базе данных */
    void add(Project_ImageURLs project_ImageURLs) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Project_ImageURLs get(int projectId, String imageURL) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Project_ImageURLs project_ImageURLs) throws SQLException;

    /** Удаляет объект  */
    public void delete(Project_ImageURLs project_ImageURLs) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Project_ImageURLs> getAll() throws SQLException;


}




