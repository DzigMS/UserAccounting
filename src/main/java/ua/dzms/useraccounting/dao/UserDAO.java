package ua.dzms.useraccounting.dao;

import ua.dzms.useraccounting.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class UserDAO implements DAO{
    public List<User> getList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            user.setFirstName(resultSet.getNString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setDateOfBirth((resultSet.getDate("dateOfBirth")).toLocalDate());
            users.add(user);
        }
        return users;
    }
}
