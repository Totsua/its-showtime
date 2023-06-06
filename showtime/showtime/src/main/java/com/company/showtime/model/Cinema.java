package com.company.showtime.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Cinema {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cinemaName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int cinemaId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cinemaAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cinemaCity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cinemaPostcode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int cinemaDistance;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String time;

    // Constructors
    public Cinema(String cinemaName) {this.cinemaName = cinemaName;}

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
                  String cinemaPostcode, int cinemaDistance, String date, String time){
        this.cinemaName = cinemaName;
        this.cinemaId = cinemaId;
        this.cinemaAddress = cinemaAddress;
        this.cinemaCity = cinemaCity;
        this.cinemaPostcode = cinemaPostcode;
        this.cinemaDistance = cinemaDistance;
        this.date = date;
        this.time = time;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
