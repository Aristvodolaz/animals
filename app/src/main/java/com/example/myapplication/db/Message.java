package com.example.myapplication.db;


import java.util.Date;

public class Message {
    long id;
    String mesage_text;
    String message_user;
    int type; //todo 0 - не отправлено, 1 - отправлено, 2 - доставлено, 3 - прочитано
    private long time;

    public Message(long id, String mesage_text, String message_user, int type) {
        this.id = id;
        this.mesage_text = mesage_text;
        this.message_user = message_user;
        this.type = type;
        time = new Date().getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMesage_text() {
        return mesage_text;
    }

    public void setMesage_text(String mesage_text) {
        this.mesage_text = mesage_text;
    }

    public String getMessage_user() {
        return message_user;
    }

    public void setMessage_user(String message_user) {
        this.message_user = message_user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
