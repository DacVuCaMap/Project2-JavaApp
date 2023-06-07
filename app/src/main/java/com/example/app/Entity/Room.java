package com.example.app.Entity;

import com.example.app.Entity.Enum.RoomType;
import com.example.app.Entity.Enum.StatusRoom;

public class Room {
    private String roomId;
    private String roomNumber;
    private double price;
    private RoomType roomType;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private StatusRoom status;
    private Apartment apartment;
    private String desRoom;
    private Client client;

    //define enum
    public Room(){;}

    public Room(String roomId, String roomNumber, double price, RoomType roomType, String img1, String img2, String img3, String img4, String img5, StatusRoom status, Apartment apartment, String desRoom, Client client) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.status = status;
        this.apartment = apartment;
        this.desRoom = desRoom;
        this.client = client;
    }

    public Room(String roomId, String roomNumber, double price, RoomType roomType, String img1, String img2, String img3, String img4, String img5, StatusRoom status, Apartment apartment, String desRoom) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.status = status;
        this.apartment = apartment;
        this.desRoom = desRoom;
    }

    public Room(String roomNumber, double price, RoomType roomType, StatusRoom status, String desRoom) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.status = status;
        this.desRoom = desRoom;
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public StatusRoom getStatus() {
        return status;
    }

    public void setStatus(StatusRoom status) {
        this.status = status;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getDesRoom() {
        return desRoom;
    }

    public void setDesRoom(String desRoom) {
        this.desRoom = desRoom;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                ", img4='" + img4 + '\'' +
                ", img5='" + img5 + '\'' +
                ", status=" + status +
                ", apartment=" + apartment +
                ", desRoom='" + desRoom + '\'' +
                ", client=" + client +
                '}';
    }
}
