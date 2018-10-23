package com.sport.team.dao;

import com.sport.team.entity.Comment;
import com.sport.team.entity.Skill;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {

    /** сохраняет объект в базе данных */
    void add(Comment comment) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Comment get(int id) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Comment comment) throws SQLException;

    /** Удаляет объект  */
    public void delete(Comment comment) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Comment> getAll() throws SQLException;


}




