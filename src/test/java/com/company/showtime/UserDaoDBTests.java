package com.company.showtime;

import com.company.showtime.dao.UserDAODB;
import com.company.showtime.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the User Database DAO
 * The class is a @SpringBootTest in order to run the application
 * alongside the tests to get it to work properly.
 */
@SpringBootTest
public class UserDaoDBTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDAODB userDao;

    /**
     * Testing the getUserByUsername method
     */
    @Test
    @DisplayName("Get User By Username Test")
    public void getUserByUsername(){
        String username = "totsua";
        User testUser = userDao.getUserByUsername(username);
        assertEquals("totsua", testUser.getUsername());
    }

    /**
     * Testing the saveUser method
     * The test will add the user to the database,
     * verify it saved, then delete it afterward.
     */
    @Test
    @DisplayName("Saving A New User Test")
    public void saveUser(){
        User testUser = new User(0,"TESTUSER","TESTING");
        userDao.saveUser(testUser);
        User testUserBack = userDao.getUserByUsername("TESTUSER");
        assertEquals("TESTUSER", testUserBack.getUsername());
        String sqlQuery = "DELETE FROM user WHERE username = ?;";
        // Connect to database with Query and parameters
        jdbcTemplate.update(sqlQuery, testUser.getUsername());
    }
}
