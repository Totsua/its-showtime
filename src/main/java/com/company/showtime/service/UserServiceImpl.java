package com.company.showtime.service;

import com.company.showtime.dao.UserDAO;
import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.User;
import com.company.showtime.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *          --UNDER CONSTRUCTION--
 *          Will be done Next Patch
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    ApiService apiService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDAO userDAO;

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

    @Override
    public void saveUserCinema(String cinemaId) throws CustomException {
        String username = getCurrentUserUsername();
        Cinema cinema = apiService.getCinemaDetails(cinemaId);
        userDAO.saveUserCinema(username,cinema);
    }

    private String getCurrentUserUsername(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){

            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            return user.getUsername();
        }else{
            return null;
        }

    }

}
