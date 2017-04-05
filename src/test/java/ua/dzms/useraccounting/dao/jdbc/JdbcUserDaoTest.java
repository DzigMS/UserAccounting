package ua.dzms.useraccounting.dao.jdbc;

import org.junit.Test;
import ua.dzms.useraccounting.dao.DAO;

import static org.junit.Assert.assertNotNull;


public class JdbcUserDaoTest {
    DAO users = new JdbcUserDao();


    @Test
    public void testGetAll() throws Exception {

        assertNotNull(users.getAll());
    }

}