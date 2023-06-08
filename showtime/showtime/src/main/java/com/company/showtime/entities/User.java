package com.company.showtime.entities;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {

    /**
     *         -- SUBJECT TO CHANGE --
     *      Currently the users have an Id,
     *      Username and Password, this could
     *          change in the future.
     */
    private int id;

    @NotEmpty(message = "Username must not be empty.")
    @Size(max = 30, message = "Username must be less than 30 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty.")
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
