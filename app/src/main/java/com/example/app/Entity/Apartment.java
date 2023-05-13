package com.example.app.Entity;

public class Apartment {

    private String apartmentId;
    private String apartmentName;
    private String address;
    private String apartmentImage;
    private Host host;
    public Apartment(){;}
    public Apartment(String apartmentId, Host host, String apartmentName, String address, String apartmentImage) {
        this.apartmentId = apartmentId;
        this.host = host;
        this.apartmentName = apartmentName;
        this.address = address;
        this.apartmentImage = apartmentImage;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartmentImage() {
        return apartmentImage;
    }

    public void setApartmentImage(String apartmentImage) {
        this.apartmentImage = apartmentImage;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId='" + apartmentId + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", address='" + address + '\'' +
                ", apartmentImage='" + apartmentImage + '\'' +
                ", host=" + host +
                '}';
    }
}
