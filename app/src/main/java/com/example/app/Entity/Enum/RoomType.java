package com.example.app.Entity.Enum;

public enum RoomType {
    //define enum
    Studio("Studio"),k1n1("1k1n"),Duplex("Duplex"),k1n2("1k2n");
    private String label;

    RoomType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
