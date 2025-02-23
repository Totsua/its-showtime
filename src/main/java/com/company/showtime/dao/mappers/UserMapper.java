package com.company.showtime.dao.mappers;

import com.company.showtime.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserMapper implements RowMapper<User> {

   /**
    * The Mapper for the User Database.
    * Responsible for creating a User Object from the database.
    * @param resultSet contains the data from the database.
    * @param index the row that is being mapped.
    * @return User object from the mapped data.
    * @throws SQLException if there is an error while accessing the database.
    */


    public User mapRow(ResultSet resultSet,int index) throws SQLException{
       User user = new User();
       user.setId(resultSet.getInt("userID"));
       user.setUsername(resultSet.getString("username"));
       user.setPassword(resultSet.getString("password"));
       return user;
   }
}
