package com.company.showtime.service;

import com.company.showtime.entities.User;
import com.company.showtime.exceptions.CustomException;


public interface UserService {
    void saveUser(User user);
    User findUserByUsername(String username);
    void saveUserCinema(String id) throws CustomException;
}
