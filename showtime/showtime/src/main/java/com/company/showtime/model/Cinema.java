package com.company.showtime.model;

public class Cinema {
    private String cinemaName;
    private int cinemaId;
    private String cinemaAddress;
    private String cinemaCity;
    private String cinemaPostcode;
    private int cinemaDistance;
    private String filmDate;
    private String filmTime;

    // Constructors
    public Cinema(String cinemaName, int cinemaId, String cinemaAddress, String cinemaCity,
                  String cinemaPostcode, int cinemaDistance) {
        this.cinemaName = cinemaName;
        this.cinemaId = cinemaId;
        this.cinemaAddress = cinemaAddress;
        this.cinemaCity = cinemaCity;
        this.cinemaPostcode = cinemaPostcode;
        this.cinemaDistance = cinemaDistance;
    }

    public Cinema(String cinemaName, int cinemaId, String cinemaAddress, String cinemaCity,
                  String cinemaPostcode, int cinemaDistance, String filmDate, String filmTime){
        this.cinemaName = cinemaName;
        this.cinemaId = cinemaId;
        this.cinemaAddress = cinemaAddress;
        this.cinemaCity = cinemaCity;
        this.cinemaPostcode = cinemaPostcode;
        this.cinemaDistance = cinemaDistance;
        this.filmDate = filmDate;
        this.filmTime = filmTime;
    }




    // Getters, Setters and toString

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public String getCinemaCity() {
        return cinemaCity;
    }

    public void setCinemaCity(String cinemaCity) {
        this.cinemaCity = cinemaCity;
    }

    public String getCinemaPostcode() {
        return cinemaPostcode;
    }

    public void setCinemaPostcode(String cinemaPostcode) {
        this.cinemaPostcode = cinemaPostcode;
    }

    public int getCinemaDistance() {
        return cinemaDistance;
    }

    public void setCinemaDistance(int cinemaDistance) {
        this.cinemaDistance = cinemaDistance;
    }

    public String getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(String filmDate) {
        this.filmDate = filmDate;
    }

    public String getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(String filmTime) {
        this.filmTime = filmTime;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaName='" + cinemaName + '\'' +
                ", cinemaId=" + cinemaId +
                ", cinemaAddress='" + cinemaAddress + '\'' +
                ", cinemaCity='" + cinemaCity + '\'' +
                ", cinemaPostcode='" + cinemaPostcode + '\'' +
                '}';
    }
}
