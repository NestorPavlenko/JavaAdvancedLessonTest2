package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.dao.impl.UserDaoimpl;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private static UserService userServiceImpl;

    private UserDao userDao;
    private UserServiceImpl () {
        try {
            userDao = new UserDaoimpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOGGER.error(e);
        }
    }

    public static UserService getUserService () {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User create(User t) {
        return userDao.create(t);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public User update(User t) {
        return userDao.update(t);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }
}
