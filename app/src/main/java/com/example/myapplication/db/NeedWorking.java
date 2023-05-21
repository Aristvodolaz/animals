package com.example.myapplication.db;

public class NeedWorking {
    String name;
    String surname;
    String phone;
    String address;
    String animals;
    String poroda;
    String days;

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

    public String getAnimals() {
        return animals;
    }

    public void setAnimals(String animals) {
        this.animals = animals;
    }

    public String getPoroda() {
        return poroda;
    }

    public void setPoroda(String poroda) {
        this.poroda = poroda;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public NeedWorking(String name, String surname, String phone, String address, String animals, String poroda, String days) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.animals = animals;
        this.poroda = poroda;
        this.days = days;
    }
}
