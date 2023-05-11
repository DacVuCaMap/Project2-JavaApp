package com.example.app.Entity;

public class Room {
    private String roomId;
    private String roomType;
    private int roomNumber;
    private double roomPrice;
    private String describe;
    private Apartment apartment;
    private String image;
    public Room(){;}

    public Room(String roomId, String roomType, int roomNumber, double roomPrice, String describe, Apartment apartment, String image) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.describe = describe;
        this.apartment = apartment;
        this.image = image;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomNumber=" + roomNumber +
                ", roomPrice=" + roomPrice +
                ", describe='" + describe + '\'' +
                ", apartment=" + apartment +
                ", image='" + image + '\'' +
                '}';
    }
}
