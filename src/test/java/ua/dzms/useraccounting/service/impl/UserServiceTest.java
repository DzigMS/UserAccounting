package ua.dzms.useraccounting.service.impl;

import org.junit.Test;
import ua.dzms.useraccounting.service.Service;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void testGetAll() throws Exception {
        Service users = new UserService();
        assertNotNull(users.getAll());
    }

}