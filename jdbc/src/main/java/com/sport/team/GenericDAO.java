package com.sport.team;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO <T> {

    /** сохраняет объект в базе данных */
    public T create(T object);

    /** Извлекает объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    public T get(int id);

    /** Сохраняет изменения, сделанные в объекте.  */
    public void update (T object);

    /** Удаляет объект  */
    public void delete(T object);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() throws SQLException;


}




