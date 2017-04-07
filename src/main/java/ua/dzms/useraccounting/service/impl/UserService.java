package ua.dzms.useraccounting.service.impl;

import ua.dzms.useraccounting.dao.DAO;
import ua.dzms.useraccounting.dao.jdbc.JdbcUserDao;
import ua.dzms.useraccounting.entity.User;
import ua.dzms.useraccounting.service.Service;

import java.util.List;

public class UserService implements Service {
    private DAO dao = new JdbcUserDao();

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void addUser(User newUser) {
        System.out.println("Add");
//        dao.addUser(newUser);
    }

    @Override
    public void removeUser(User removeUser) {

    }

    @Override
    public void editUser(User editUser) {
        System.out.println("Edit");
    }
}
