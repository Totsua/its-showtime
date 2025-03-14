package com.company.showtime.dao.mappers;

import com.company.showtime.entities.Cinema;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaMapper implements RowMapper<Cinema> {

    @Override
    public Cinema mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cinema cinema = new Cinema();
        cinema.setCinemaId(rs.getInt("cinemaId"));
        cinema.setCinemaName(rs.getString("name"));
        cinema.setCinemaName(rs.getString("address"));
        cinema.setCinemaName(rs.getString("postcode"));
        return cinema;

    }
}
