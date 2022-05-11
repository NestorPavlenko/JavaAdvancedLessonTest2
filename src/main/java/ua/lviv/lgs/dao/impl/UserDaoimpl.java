package ua.lviv.lgs.dao.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserCRUD;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoimpl implements UserCRUD, UserDao {

    private static String READ_ALL = "select * from user";
    private static String CREATE = "insert into user(`email`, `first_name`, `last_name`, `role`, `password`) values (?,?,?,?,?)";
    private static String READ_BY_ID = "select * from user where id = ?";
    private static String UPDATE_BY_ID = "update user set email = ?, first_name = ?, last_name = ?, role = ?, password = ? where id = ?";
    private static String DELETE_BY_ID = "delete from user where id = ?";

    private static Logger LOGGER = Logger.getLogger(UserDaoimpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDaoimpl () throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } catch (SQLException throwables) {
            LOGGER.error(throwables);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Integer userId = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            user = new User (userId, email, firstName, lastName, role, password);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }

    @Override
    public User update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> userRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer userId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                userRecords.add(new User (userId, email, firstName, lastName, role, password));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return userRecords;
    }
}
