package com.company.showtime.dao;

import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.User;

public interface UserDAO {
    User getUserByUsername(String username);
    User saveUser(User user);
    void saveUserCinema(String username, Cinema cinema);
}
