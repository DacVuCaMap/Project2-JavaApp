package com.example.app.Entity;

import java.time.LocalDate;

public class Monthly {
    private int id;
    private Contract contract;
    private String status;
    private LocalDate date;

    public Monthly(int id, Contract contract, String status, LocalDate date) {
        this.id = id;
        this.contract = contract;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Monthly{" +
                "id=" + id +
                ", contract=" + contract +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
