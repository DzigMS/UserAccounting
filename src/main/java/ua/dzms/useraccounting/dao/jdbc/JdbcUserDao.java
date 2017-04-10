package ua.dzms.useraccounting.dao.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import ua.dzms.useraccounting.dao.UserDao;
import ua.dzms.useraccounting.dao.mapper.UserMapper;
import ua.dzms.useraccounting.entity.User;

import java.sql.*;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final String SELECT_ALL_USER = "SELECT u.id, u.firstName, u.lastName, u.dateOfBirth FROM user AS u;";
    private static final String ADD_USER = "INSERT INTO user (firstName, lastName, dateOfBirth) VALUES (? , ?, ?); ";
    private static final String UPDATE_USER = "UPDATE user SET firstName = ?, lastName = ?, dateOfBirth = ? WHERE id = ?;";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?;";
    private UserMapper userMapper = new UserMapper();
    private BasicDataSource dataSource;

    public JdbcUserDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return userMapper.map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addUser(User newUser) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
        ) {
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getLastName());
            preparedStatement.setDate(3, Date.valueOf(newUser.getDateOfBirth()));
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Add error");
        }

    }

    @Override
    public void removeUser(User removeUser) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        ) {
            preparedStatement.setInt(1, removeUser.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("remove error");
        }
    }

    @Override
    public void updateUser(User updateUser) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        ) {
            preparedStatement.setString(1, updateUser.getFirstName());
            preparedStatement.setString(2, updateUser.getLastName());
            preparedStatement.setDate(3, Date.valueOf(updateUser.getDateOfBirth()));
            preparedStatement.setInt(4, updateUser.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update error");
        }

    }
}
