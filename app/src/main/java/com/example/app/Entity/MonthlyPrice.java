package com.example.app.Entity;

public class MonthlyPrice {
    private double electricPrice;
    private double waterPrice;
    private double servicePrice;

    public MonthlyPrice(double electricPrice, double waterPrice, double servicePrice) {
        this.electricPrice = electricPrice;
        this.waterPrice = waterPrice;
        this.servicePrice = servicePrice;
    }

    public double getElectricPrice() {
        return electricPrice;
    }

    public void setElectricPrice(double electricPrice) {
        this.electricPrice = electricPrice;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
