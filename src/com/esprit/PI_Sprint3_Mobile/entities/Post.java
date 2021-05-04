package com.esprit.PI_Sprint3_Mobile.entities;

import com.codename1.facebook.User;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String text;
    private Sujet sujet;
    private String image;
    private User user;
    private int rating;
    private LocalDateTime dateTime;

    public Post() {
        dateTime = LocalDateTime.now();
    }

    public Post(String text, Sujet sujet, int rating ){
        dateTime = LocalDateTime.now();
        this.text = text;
        this.sujet = sujet;
        this.rating = rating;
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

    public Sujet getSujet() {
        return sujet;
    }

    public String getImage() {
        return image;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", sujet=" + sujet +
                ", image='" + image + '\'' +
                ", user=" + user +
                ", rating=" + rating +
                ", dateTime=" + dateTime +
                '}';
    }

}
