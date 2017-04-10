package ua.dzms.useraccounting.service.impl;

import ua.dzms.useraccounting.dao.UserDao;
import ua.dzms.useraccounting.entity.User;
import ua.dzms.useraccounting.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void addUser(User newUser) {
        dao.addUser(newUser);
    }

    @Override
    public void removeUser(User removeUser) {
        dao.removeUser(removeUser);
    }

    @Override
    public void editUser(User editUser) {
        dao.updateUser(editUser);
    }
}
