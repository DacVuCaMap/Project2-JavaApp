package com.example.app.Entity;

import java.time.LocalDate;

public class Contract {
    private String contractId;
    private LocalDate startDate;
    private LocalDate startMonth;
    private LocalDate endMonth;
    private String description;
    private Client client;
    private Room room;
    private Double totalPrice;

    public Contract(String contractId, LocalDate startDate, LocalDate startMonth, LocalDate endMonth, String description, Client client, Room room, Double totalPrice) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.description = description;
        this.client = client;
        this.room = room;
        this.totalPrice = totalPrice;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(LocalDate startMonth) {
        this.startMonth = startMonth;
    }

    public LocalDate getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(LocalDate endMonth) {
        this.endMonth = endMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId='" + contractId + '\'' +
                ", startDate=" + startDate +
                ", startMonth=" + startMonth +
                ", endMonth=" + endMonth +
                ", description='" + description + '\'' +
                ", client=" + client +
                ", room=" + room +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
