package ua.dzms.useraccounting.dao.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.dzms.useraccounting.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


public class UserMapperTest {
    UserMapper userMapper = new UserMapper();

    @Test
    public void testMap() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(resultSet.getString("firstName")).thenReturn("Alex").thenReturn("Vasia");
        when(resultSet.getString("lastName")).thenReturn("Ivanov").thenReturn("Petrov");
        when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf("1990-3-3")).thenReturn(Date.valueOf("1995-12-7"));

        List<User> users = userMapper.map(resultSet);

        assertEquals(2 , users.size());

        User user = users.get(0);
        assertEquals(1, user.getId());
        assertEquals("Alex", user.getFirstName());
        assertEquals("Ivanov", user.getLastName());
        assertEquals(LocalDate.of(1990,3,3), user.getDateOfBirth());

        user = users.get(1);
        assertEquals(2, user.getId());
        assertEquals("Vasia", user.getFirstName());
        assertEquals("Petrov", user.getLastName());
        assertEquals(LocalDate.of(1995, 12, 7), user.getDateOfBirth());


    }
}