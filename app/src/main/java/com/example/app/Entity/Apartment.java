package com.example.app.Entity;

public class Apartment {
    private String apartermentId;
    private String apartermentName;
    private String address;
    private String image;
    private Host host;
    public Apartment(){;}

    public Apartment(String apartermentId, String apartermentName, String address, String image, Host host) {
        this.apartermentId = apartermentId;
        this.apartermentName = apartermentName;
        this.address = address;
        this.image = image;
        this.host = host;
    }

    public String getApartermentId() {
        return apartermentId;
    }

    public void setApartermentId(String apartermentId) {
        this.apartermentId = apartermentId;
    }

    public String getApartermentName() {
        return apartermentName;
    }

    public void setApartermentName(String apartermentName) {
        this.apartermentName = apartermentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Aparterment{" +
                "apartermentId='" + apartermentId + '\'' +
                ", apartermentName='" + apartermentName + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", host=" + host +
                '}';
    }
}
