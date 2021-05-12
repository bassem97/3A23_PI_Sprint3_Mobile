package com.esprit.PI_Sprint3_Mobile.entities;


import java.time.LocalDateTime;

public class Sujet {
    private int id;
    private String text;
    private Theme theme;
    private String image;
    private User user;
    private LocalDateTime dateTime;

    public Sujet() {
        dateTime = LocalDateTime.now();
    }

    public Sujet( String text, Theme theme) {
        dateTime = LocalDateTime.now();
        this.text = text;
        this.theme = theme;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Sujet{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", theme=" + theme +
                ", image='" + image + '\'' +
                ", user=" + user +
                ", dateTime=" + dateTime +
                '}';
    }

}
