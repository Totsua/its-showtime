package com.company.showtime.model;

import java.util.List;

public class Film {
    private int filmID;
    private String filmName;
    private String filmSynopsis;
    private String filmAgeRating;
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


    public String toString(boolean includesSynopsis) {
        if (includesSynopsis) {
            return "Film{" +
                    "filmID=" + filmID +
                    ", filmName='" + filmName + '\'' +
                    ", filmAgeRating='" + filmAgeRating + '\'' +
                    ", showtimes=" + showtimes +
                    '}';
        }
        else {
            return "Film{" +
                    "filmID=" + filmID +
                    ", filmName='" + filmName + '\'' +
                    ", filmSynopsis='" + filmSynopsis + '\'' +
                    ", filmAgeRating='" + filmAgeRating + '\'' +
                    '}';
        }
    }
}
