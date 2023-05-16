package com.example.myapplication.db;

public class Perederzhka {

    String name;
    String surname;
    String phone;
    String city;
    String type_animals;
    String description;
    String imgURL;
    String price;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Perederzhka(String name, String surname, String phone, String city, String type_animals, String description, String imgURL, String price) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.city = city;
        this.type_animals = type_animals;
        this.description = description;
        this.imgURL = imgURL;
        this.price = price;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType_animals() {
        return type_animals;
    }

    public void setType_animals(String type_animals) {
        this.type_animals = type_animals;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
