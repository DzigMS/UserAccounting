package ua.dzms.useraccounting.service;

import ua.dzms.useraccounting.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    void addUser(User newUser);
    void removeUser(User removeUser);
    void editUser(User editUser);
}
