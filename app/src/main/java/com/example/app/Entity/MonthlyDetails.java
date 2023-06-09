package com.example.app.Entity;

import java.time.LocalDate;

public class MonthlyDetails {
    private String id;
    private Client client;
    private Room room;
    private double electricPrice;
    private int electricNumber;
    private double waterPrice;
    private int waterNumber;
    private double servicePrice;
    private LocalDate create_at;
    private String month_year;

    public MonthlyDetails(String id, Client client, Room room, double electricPrice, int electricNumber, double waterPrice, int waterNumber, double servicePrice, LocalDate create_at, String month_year) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.electricPrice = electricPrice;
        this.electricNumber = electricNumber;
        this.waterPrice = waterPrice;
        this.waterNumber = waterNumber;
        this.servicePrice = servicePrice;
        this.create_at = create_at;
        this.month_year = month_year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getElectricPrice() {
        return electricPrice;
    }

    public void setElectricPrice(double electricPrice) {
        this.electricPrice = electricPrice;
    }

    public int getElectricNumber() {
        return electricNumber;
    }

    public void setElectricNumber(int electricNumber) {
        this.electricNumber = electricNumber;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public int getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(int waterNumber) {
        this.waterNumber = waterNumber;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public String getMonth_year() {
        return month_year;
    }

    public void setMonth_year(String month_year) {
        this.month_year = month_year;
    }
}
