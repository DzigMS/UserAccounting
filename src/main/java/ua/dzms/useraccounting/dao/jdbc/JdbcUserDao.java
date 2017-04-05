package ua.dzms.useraccounting.dao.jdbc;

import ua.dzms.useraccounting.dao.UserDAO;
import ua.dzms.useraccounting.entity.User;

import java.sql.*;
import java.util.List;

public class JdbcUserDao extends UserDAO {
    private static final String SELECT_ALL_USER = "SELECT u.firstName, u.lastName, u.dateOfBirth FROM user AS u;";
    private static final String ADD_USER = "INSERT INTO user (firstName, lastName, dateOfBirth) VALUES (? , ?, ?) ";

    @Override
    public List<User> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return getList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addUser(User newUser) {
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
                ) {
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getLastName());
            preparedStatement.setDate(3, Date.valueOf(newUser.getDateOfBirth()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void updateUser(User updateUser) {

    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/useraccount";
        String login = "root";
        String password = "root";
        return DriverManager.getConnection(url, login, password);
    }
}
