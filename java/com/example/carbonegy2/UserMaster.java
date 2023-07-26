package com.example.carbonegy2;

public class UserMaster {

    private int user_id;
    private String name;
    private String email;
    private String phone;
    private int average_emission;

    public UserMaster() {
    }

    public UserMaster(String name, String email, String phone, int average_emission) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.average_emission = average_emission;
    }

    public UserMaster(int user_id, String name, String email, String phone, int average_emission) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.average_emission = average_emission;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAverageEmission() {
        return average_emission;
    }

    public void setAverageEmission(int average_emission) {
        this.average_emission = average_emission;
    }
}
