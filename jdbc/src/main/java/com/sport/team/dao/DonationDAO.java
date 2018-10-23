package com.sport.team.dao;

import com.sport.team.entity.Donation;
import com.sport.team.entity.Skill;

import java.sql.SQLException;
import java.util.List;

public interface DonationDAO {

    /** сохраняет объект в базе данных */
    void add(Donation donation) throws SQLException;

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public Donation get(int id) throws SQLException;

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update(Donation donation) throws SQLException;

    /** Удаляет объект  */
    public void delete(Donation donation) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Donation> getAll() throws SQLException;


}




