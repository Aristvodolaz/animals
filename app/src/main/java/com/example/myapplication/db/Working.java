package com.example.myapplication.db;

public class Working {
    String name;
    String surname;
    String phone;
    String age;
    String city;
    String work;
    String imgURL;
    String price;

    public Working(String name, String surname, String phone, String age, String city, String work, String imgURL, String price) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.age = age;
        this.city = city;
        this.work = work;
        this.imgURL = imgURL;
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
