package com.esprit.PI_Sprint3_Mobile.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.time.LocalDateTime;

public class Post {

    private int id;
    private String text;
    private Sujet sujet;
    private String image;
    private User user;
    private ImageView imageView;
    private int rating;
    private LocalDateTime dateTime;

    public Post() {imageView = new ImageView();
        dateTime = LocalDateTime.now();
    }

    public Post(String text, Sujet sujet, int rating ){
        dateTime = LocalDateTime.now();
        this.text = text;
        this.sujet = sujet;
        this.rating = rating;
        imageView = new ImageView();
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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
                ", imageView=" + imageView +
                ", rating=" + rating +
                ", dateTime=" + dateTime +
                '}';
    }

    public void initializeImageView() {
        File file = new File("src/assets/" + image);
        try {
            Image img = new Image(file.toURI().toString());
            imageView.setImage(img);
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
