package com.sport.team.dao;

import com.sport.team.entity.User;
import com.sport.team.entity.Users_Skills;

import java.sql.SQLException;
import java.util.List;

public interface Users_Skills_DAO {

    /** сохраняет объект в базе данных */
    void add(Users_Skills users_skills) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Users_Skills get(int userId,int skillId) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Users_Skills users_skills) throws SQLException;

    /** Удаляет объект  */
    public void delete(Users_Skills users_skills) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Users_Skills> getAll() throws SQLException;


}




