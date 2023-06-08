package com.company.showtime.dao;

import com.company.showtime.dao.mappers.UserMapper;
import com.company.showtime.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
    private final JdbcTemplate jdbc;
    public UserDAODB(JdbcTemplate jdbc){this.jdbc = jdbc;}

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
     * Method to save a user to the database.
     * Usernames must be unique.
     * The UserID is generated in the table and set to the User Object after.
     * (Potentially should use a constructor that doesn't take ID).
     * @param user - The user to save into the database
     * @return the User,
     * If the username is taken, @return the user with username "USERNAME IS TAKEN",
     * Any database access problems - @return null.
     */
    @Override
    public User saveUser(User user) {
        final String INSERT_USER = "INSERT INTO user(username,password)" +
                "VALUES(?,?)";
        try{jdbc.update(INSERT_USER,
                user.getUsername(),
                user.getPassword());
        final String USERID = "SELECT userID FROM user WHERE username = ?";
        int newId = jdbc.queryForObject(USERID, new UserMapper(), user.getUsername(),Integer.class).getId();
        user.setId(newId);
        return user;
        } catch(DuplicateKeyException e){
            user.setUsername("USERNAME IS TAKEN");
            return user;
        }
        catch (DataAccessException e){
            return null;
        }
    }
}
