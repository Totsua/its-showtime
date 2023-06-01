package com.company.showtime.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Film {
    // @JsonInclude(JsonInclude.Include.NON_NULL) - Won't display them if they are null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int filmID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filmName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filmAgeRating;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filmSynopsis;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> showtimes;

    // Constructors
    public Film(int filmID, String filmName, String filmAgeRating, List<String> showtimes){
        this.filmID = filmID;
        this.filmName = filmName;
        this.showtimes = showtimes;
        this.filmAgeRating = filmAgeRating;
    }

    public Film(int filmID, String filmName, String filmSynopsis, String filmAgeRating) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.filmSynopsis = filmSynopsis;
        this.filmAgeRating = filmAgeRating;
    }

    // Getters, Setters and toString


    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmSynopsis() {
        return filmSynopsis;
    }

    public void setFilmSynopsis(String filmSynopsis) {
        this.filmSynopsis = filmSynopsis;
    }

    public String getFilmAgeRating() {
        return filmAgeRating;
    }

    public void setFilmAgeRating(String filmAgeRating) {
        this.filmAgeRating = filmAgeRating;
    }

    public List<String> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<String> showtimes) {
        this.showtimes = showtimes;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Film{" +
                "filmID=" + filmID +
                ", filmName='" + filmName + '\'' +
                ", filmAgeRating='" + filmAgeRating + '\'');
        if (filmSynopsis !=null){
            builder.append(", filmSynopsis= " +filmSynopsis+ '\'' +"}");
        }
        if (showtimes!=null){
            builder.append(", showtimes=" + showtimes + '}');
        }
        return builder.toString();
    }
}
