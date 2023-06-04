package com.example.app.Entity.Enum;

public enum StatusRoom {
    AVAILABLE("AVAILABLE"),OCCUPIED("OCCUPIED"),MAINTENANCE("MAINTENANCE");

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

    public static StatusRoom fromValue(String value) {
        for (StatusRoom status : StatusRoom.values()) {
            if (status.getLabel().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
