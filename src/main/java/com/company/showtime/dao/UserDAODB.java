package com.company.showtime.dao;

import com.company.showtime.dao.mappers.CinemaMapper;
import com.company.showtime.dao.mappers.UserMapper;
import com.company.showtime.entities.Cinema;
import com.company.showtime.entities.User;
import com.company.showtime.exceptions.DatabaseException;
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
     * The UserID is generated in the table.
     * @param user - The user to save into the database
     * @return the User or null if database problems
     */
    @Override
    public User saveUser(User user) {
        final String INSERT_USER = "INSERT INTO user(username,password)" +
                "VALUES(?,?)";
        try{jdbc.update(INSERT_USER,
                user.getUsername(),
                user.getPassword());
        final String USERID = "SELECT userID FROM user WHERE username = ?";
        return user;
        }
        catch (DataAccessException| NullPointerException  e){
            return null;
        }
    }

    @Override
    public void saveUserCinema(String username, Cinema cinema) {
        if(!isCinemaInDB(cinema)){
            saveCinema(cinema);
        }

        try{
            final String INSERT_USERCINEMA = "INSERT INTO User_Cinema (userID, cinemaId)" +
                    "SELECT u.userID, c.cinemaId" +
                    "FROM user u" +
                    "JOIN Cinema c ON c.cinemaId = ?" +
                    "WHERE u.username = ?";
            jdbc.update(INSERT_USERCINEMA, cinema.getCinemaId(), username );
        }catch (DataAccessException e){
            // todo: log the error and give the ui an appropriate message (14/03/25)

            throw new DatabaseException(e.getMessage());
        }


    }

    private boolean isCinemaInDB(Cinema cinema){
        final String SELECT_CINEMA = "SELECT * FROM cinema WHERE cinemaId = ?";
        try{
            jdbc.query(SELECT_CINEMA, new CinemaMapper(),cinema.getCinemaId());
        }catch(DataAccessException|NullPointerException e){
            return false;
            }

        return true;
    }

    private void saveCinema(Cinema cinema){
        try{
            final String INSERT_CINEMA = "INSERT INTO cinema(cinemaid,name,address,postcode) VALUES (?,?,?,?)";
            jdbc.update(INSERT_CINEMA,cinema.getCinemaId(),cinema.getCinemaName(),
                    cinema.getCinemaAddress(),cinema.getCinemaPostcode());
        }catch (DataAccessException e){
            // todo: log the error and give the ui an appropriate message (14/03/25)
            throw new DatabaseException(e.getMessage());
        }
    }
}
