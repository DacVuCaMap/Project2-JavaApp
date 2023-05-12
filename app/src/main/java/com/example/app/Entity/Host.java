package com.example.app.Entity;

import java.time.LocalDate;
public class Host {
    private String hostId;
    private String hostName;
    private LocalDate dob;
    private String address;
    private String citizenId;
    private String hostImage;
    private String hostEmail;
    private String hostPhone;
    public Host(){;}

    public Host(String hostId, String hostName, LocalDate dob, String address, String citizenId, String hostImage, String hostEmail, String hostPhone) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.dob = dob;
        this.address = address;
        this.citizenId = citizenId;
        this.hostImage = hostImage;
        this.hostEmail = hostEmail;
        this.hostPhone = hostPhone;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getHostImage() {
        return hostImage;
    }

    public void setHostImage(String hostImage) {
        this.hostImage = hostImage;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public String getHostPhone() {
        return hostPhone;
    }

    public void setHostPhone(String hostPhone) {
        this.hostPhone = hostPhone;
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostId='" + hostId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", citizenId='" + citizenId + '\'' +
                ", hostImage='" + hostImage + '\'' +
                ", hostEmail='" + hostEmail + '\'' +
                ", hostPhone='" + hostPhone + '\'' +
                '}';
    }
}
