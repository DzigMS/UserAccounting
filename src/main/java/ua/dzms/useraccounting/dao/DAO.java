package ua.dzms.useraccounting.dao;

import ua.dzms.useraccounting.entity.User;

import java.util.List;

public interface DAO {
    List<User> getAll();
    void addUser(User newUser);
    void removeUser(User user);
    void updateUser(User updateUser);
}
