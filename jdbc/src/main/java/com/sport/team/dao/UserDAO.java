package com.sport.team.dao;

import com.sport.team.entity.User;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO{

    /** сохраняет объект в базе данных */
    void add(User user) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public User get(int id);

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update (User user);

    /** Удаляет объект  */
    public void delete(User user);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<User> getAll() throws SQLException;


}




