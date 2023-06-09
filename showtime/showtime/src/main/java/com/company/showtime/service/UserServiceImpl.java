package com.company.showtime.service;

import com.company.showtime.dao.UserDAO;
import com.company.showtime.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *          --UNDER CONSTRUCTION--
 *          Will be done Next Patch
 */
@Service
public class UserServiceImpl implements UserService{

    private PasswordEncoder passwordEncoder;
    private UserDAO userDAO;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDAO userDAO){
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    /**
     * Method to encode the users password and save them to
     * the database.
     * Maybe a User DTO should be used for security.
     * @param user - user being saved into database.
     */
    @Override
    public void saveUser(User user) {
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    /**
     * Method for finding a user within the database.
     * @param username - username of the user trying to be found
     * @return the user
     */
    @Override
    public User findUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
