package com.sport.team.dao;

import com.sport.team.entity.Users_Skills;
import com.sport.team.entity.Users_Tools;

import java.sql.SQLException;
import java.util.List;

public interface Users_Tools_DAO {

    /** сохраняет объект в базе данных */
    void add(Users_Tools users_Tools) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Users_Tools get(int userId, int toolId) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Users_Tools users_Tools) throws SQLException;

    /** Удаляет объект  */
    public void delete(Users_Tools users_Tools) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Users_Tools> getAll() throws SQLException;


}




