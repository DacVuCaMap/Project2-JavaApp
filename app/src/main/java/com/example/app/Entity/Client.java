package com.example.app.Entity;

import java.time.LocalDate;

public class Client {
    private String clientId;
    private String clientImage;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String clientAddress;
    private LocalDate clientDOB;
    private String citizenID;
    private Room room;
    public Client(String clientId, String clientImage, String clientName, String clientEmail, String clientPhone, String clientAddress, LocalDate clientDOB, String citizenID) {
        this.clientId = clientId;
        this.clientImage = clientImage;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.clientDOB = clientDOB;
        this.citizenID = citizenID;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientImage() {
        return clientImage;
    }

    public void setClientImage(String clientImage) {
        this.clientImage = clientImage;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public LocalDate getClientDOB() {
        return clientDOB;
    }

    public void setClientDOB(LocalDate clientDOB) {
        this.clientDOB = clientDOB;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", clientImage='" + clientImage + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientDOB=" + clientDOB +
                ", citizenID='" + citizenID + '\'' +
                ", room=" + room +
                '}';
    }
}
