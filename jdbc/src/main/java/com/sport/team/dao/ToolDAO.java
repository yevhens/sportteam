package com.sport.team.dao;

import com.sport.team.entity.Tool;

import java.sql.SQLException;
import java.util.List;

public interface ToolDAO {

    /** сохраняет объект в базе данных */
    void add(Tool tool) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Tool get  (int id) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Tool tool) throws SQLException;

    /** Удаляет объект  */
    public void delete(Tool tool) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Tool> getAll() throws SQLException;


}




