package com.example.carbonegy2;

public class EmissionRecord {
    private String date;
    private int value;

    public EmissionRecord(String date, int value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }
}
