package com.company.showtime.dao;

import com.company.showtime.dao.mappers.UserMapper;
import com.company.showtime.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * The UserDAODB is still under construction
 * Will be used to contact the Database for the user table.
 */
@Repository
public class UserDAODB implements UserDAO{

    /**
     * This Project is using JDBCTemplate, it may have been easier to use JPA.
     */
    @Autowired
JdbcTemplate jdbc;

    /**
     * Method to get a user from the database with a given username.
     * @param username - The username to check if it's in the database.
     * @return the User unless there is none in the database, then return null.
     */
    @Override
    public User getUserByUsername(String username) {
        try{
            final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(),username);
            return user;
        }catch (DataAccessException e){
        return null;
        }
    }

    /**
     * Method to get a user from the database with a given username.
     * @param user - The user to save into the database
     * @return the User.
     */
    @Override
    public User saveUser(User user) {
        final String INSERT_USER = "INSERT INTO user(username,password)" +
                "VALUES(?,?)";
        jdbc.update(INSERT_USER,
                user.getUsername(),
                user.getPassword());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_userID()", Integer.class);
        user.setId(newId);
        return user;
    }
}
