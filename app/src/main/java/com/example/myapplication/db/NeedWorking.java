package com.example.myapplication.db;

public class NeedWorking {
    String name;
    String surname;
    String phone;
    String address;
    String zadacha;
    String description;
    String min_price;
    String max_price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZadacha() {
        return zadacha;
    }

    public void setZadacha(String zadacha) {
        this.zadacha = zadacha;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public NeedWorking(String name, String surname, String phone, String address, String zadacha, String description, String min_price, String max_price) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.zadacha = zadacha;
        this.description = description;
        this.min_price = min_price;
        this.max_price = max_price;
    }
}
