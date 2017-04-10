package ua.dzms.useraccounting.dao.mapper;


import ua.dzms.useraccounting.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public List<User> map(ResultSet resultSet) throws SQLException {
    List<User> users = new ArrayList<>();

        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setDateOfBirth((resultSet.getDate("dateOfBirth")).toLocalDate());
            users.add(user);
        }
        return users;
    }
}
