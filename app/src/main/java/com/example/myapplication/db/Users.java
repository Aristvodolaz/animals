package com.example.myapplication.db;

public class Users {
    int type;
    String name;
    String Surname;
    String phone;
    String age;

    String city;
    String imgURL;

    public Users(int type, String name, String surname, String phone, String age, String city, String imgURL) {
        this.type = type;
        this.name = name;
        this.Surname = surname;
        this.phone = phone;
        this.age = age;
        this.city = city;
        this.imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
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
}

