package com.company.showtime.service;

import com.company.showtime.dao.UserDAO;
import com.company.showtime.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    public CustomUserDetailsService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    /**
     * This method checks the database and loads the user via the username given when
     * a person is trying to log in.
     *
     * If the password/username is incorrect it will throw an exception.
     *
     * @param username - inputted username being checked
     * @return the user with the given authorities ("ROLE_USER" for the time being).
     * @throws UsernameNotFoundException
     */
    //javaguides.net/2018/10/user-registration-module-using-springboot-springmvc-springsecurity-hibernate5-thymeleaf-mysql.html


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);

        if(user!=null){
            // Create an instance of org.springframework.security.core.userdetails.User
            // using the user's username, password, and call them a "ROLE_USER" for now
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), authorities);
        } else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
