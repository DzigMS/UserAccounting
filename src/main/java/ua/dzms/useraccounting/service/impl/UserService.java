package ua.dzms.useraccounting.service.impl;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import ua.dzms.useraccounting.dao.DAO;
import ua.dzms.useraccounting.dao.jdbc.JdbcUserDao;
import ua.dzms.useraccounting.entity.User;
import ua.dzms.useraccounting.service.Service;

import java.util.List;
import java.util.function.Predicate;

public class UserService implements Service {
    private DAO dao = new JdbcUserDao();

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
