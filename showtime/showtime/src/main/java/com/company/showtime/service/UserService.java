package com.company.showtime.service;

import com.company.showtime.entities.User;


public interface UserService {
    void saveUser(User user);
    User findUserByUsername(String username);
}