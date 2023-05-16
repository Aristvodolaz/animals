package com.example.myapplication.db;


public class Animals {

    String adress;
    String imgURL;
    String pol;
    String description;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Animals(String adress, String imgURL, String pol, String description) {
        this.adress = adress;
        this.imgURL = imgURL;
        this.pol = pol;
        this.description = description;
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
}
