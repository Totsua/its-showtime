package com.company.showtime.model;

public class Film {
    private int filmID;
    private String filmName;
    private String filmSynopsis;
    private String filmAgeRating;

    public Film(){};

    public Film(int filmID, String filmName, String filmSynopsis, String filmAgeRating) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.filmSynopsis = filmSynopsis;
        this.filmAgeRating = filmAgeRating;
    }
// Getters and setters


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
}
