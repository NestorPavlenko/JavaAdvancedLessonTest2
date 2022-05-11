package ua.lviv.lgs.shared;

import java.sql.SQLException;
import java.util.List;

public interface AbstractCRUD<T> {
    T create (T t);
    T read (Integer id);
    T update (T t);
    void delete (Integer id) throws SQLException;
    List<T> readAll();
}
