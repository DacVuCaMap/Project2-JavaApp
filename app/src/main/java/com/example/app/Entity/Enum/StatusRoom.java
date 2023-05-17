package com.example.app.Entity.Enum;

public enum StatusRoom {
    A("AVAILABLE"),O("OCCUPIED"),M("MAINTENANCE");

    private String label;

    StatusRoom(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
