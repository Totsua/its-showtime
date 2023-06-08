package com.company.showtime.service;

import com.company.showtime.dao.UserDAO;
import com.company.showtime.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
//javaguides.net/2018/10/user-registration-module-using-springboot-springmvc-springsecurity-hibernate5-thymeleaf-mysql.html


public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    public CustomUserDetailsService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    /**
     * This method checks the database and loads the user via the username given when
     * a person is trying to log in.
     * If the password/username is incorrect it will throw an exception.
     * @param username - inputted username being checked
     * @return the user with the given authorities (None in this case).
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);

        if(user!=null){
            // Create an instance of org.springframework.security.core.userdetails.User
            // using the user's username, password, and an empty list of authorities
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), new ArrayList<>());
        } else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
