package com.example.myapplication.db;


public class AnimalsLost {
    String adress;
    String street;
    String poroda;

    String date_prodazhi;
    String imgURL;
    String pol;
    String description;
    String name;


    public String getPoroda() {
        return poroda;
    }

    public void setPoroda(String poroda) {
        this.poroda = poroda;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDate_prodazhi() {
        return date_prodazhi;
    }

    public void setDate_prodazhi(String date_prodazhi) {
        this.date_prodazhi = date_prodazhi;
    }

    public AnimalsLost(String adress, String street, String poroda, String date_prodazhi, String imgURL, String pol, String description, String name) {
        this.adress = adress;
        this.street = street;
        this.poroda = poroda;
        this.date_prodazhi = date_prodazhi;
        this.imgURL = imgURL;
        this.pol = pol;
        this.description = description;
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
