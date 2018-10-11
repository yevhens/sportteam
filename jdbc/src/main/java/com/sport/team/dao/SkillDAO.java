package com.sport.team.dao;

import com.sport.team.entity.Skill;
import com.sport.team.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface SkillDAO {

    /** сохраняет объект в базе данных */
    void add(Skill skill) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Skill get(int id);

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Skill skill);

    /** Удаляет объект  */
    public void delete(Skill skill);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Skill> getAll() throws SQLException;


}




