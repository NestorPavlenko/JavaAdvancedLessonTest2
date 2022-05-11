package ua.lviv.lgs.dao;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.User;

import java.util.List;

public interface UserDao {
    User create(User t);

    User read(Integer id);

    User update(User t);

    void delete(Integer id);

    List<User> readAll();
}
