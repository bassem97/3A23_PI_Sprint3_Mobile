package com.esprit.PI_Sprint3_Mobile.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.time.LocalDateTime;

public class Sujet {
    private int id;
    private String text;
    private Theme theme;
    private String image;
    private User user;
    private ImageView imageView;
    private ImageView ratingImg;
    private LocalDateTime dateTime;

    public Sujet() {
        imageView = new ImageView();
        ratingImg = new ImageView();
        dateTime = LocalDateTime.now();
    }

    public Sujet( String text, Theme theme) {
        dateTime = LocalDateTime.now();
        this.text = text;
        this.theme = theme;
        imageView = new ImageView();
        ratingImg = new ImageView();

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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getRatingImg() {
        return ratingImg;
    }

    public void setRatingImg(ImageView ratingImg) {
        this.ratingImg = ratingImg;
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
                ", imageView=" + imageView +
                ", ratingImg=" + ratingImg +
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

    public void setRatingImage(String imgUrl) {
        File file = new File("src/assets/" + imgUrl);
        try {
            Image img = new Image(file.toURI().toString());
            ratingImg.setImage(img);
            ratingImg.setFitWidth(50);
            ratingImg.setFitHeight(50);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
