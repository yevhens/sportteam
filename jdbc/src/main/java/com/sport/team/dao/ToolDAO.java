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
    public Tool get(int id);

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Tool tool);

    /** Удаляет объект  */
    public void delete(Tool tool);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Tool> getAll() throws SQLException;


}




