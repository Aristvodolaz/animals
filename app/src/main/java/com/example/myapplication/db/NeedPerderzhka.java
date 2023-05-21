package com.example.myapplication.db;

public class NeedPerderzhka {
    String name;
    String surname;
    String phone;
    String type_animals;
    String poroda_animals;
    String days;
    String city;

    public NeedPerderzhka(String name, String surname, String phone, String type_animals, String poroda_animals, String days, String city) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.type_animals = type_animals;
        this.poroda_animals = poroda_animals;
        this.days = days;
        this.city = city;
    }

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

    public String getType_animals() {
        return type_animals;
    }

    public void setType_animals(String type_animals) {
        this.type_animals = type_animals;
    }

    public String getPoroda_animals() {
        return poroda_animals;
    }

    public void setPoroda_animals(String poroda_animals) {
        this.poroda_animals = poroda_animals;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

