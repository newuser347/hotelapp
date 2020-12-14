package com.example.hotel;

public class Hotel {
    private String roomType;
    private double roomPrice;

    public Hotel(String roomType, double roomPrice) {
        this.roomType = roomType;
        this.roomPrice = roomPrice;

    }

    public String getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }


}

