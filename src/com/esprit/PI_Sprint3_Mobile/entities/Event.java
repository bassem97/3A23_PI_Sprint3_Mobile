package com.esprit.PI_Sprint3_Mobile.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.time.LocalDateTime;

public class Event {

    private int id, nb_part_max;
    private String name, description, image;
    private LocalDateTime date;
    private EventType eventType;
    private Club club;
    private ImageView imageView;

    public Event() {
        imageView = new ImageView();
    }

    public Event(int id, int nb_part_max, String name, String description, String image, LocalDateTime date, EventType eventType, Club club) {
        this.id = id;
        this.nb_part_max = nb_part_max;
        this.name = name;
        this.description = description;
        this.image = image;
        this.date = date;
        this.eventType = eventType;
        this.club = club;
        imageView = new ImageView();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_part_max() {
        return nb_part_max;
    }

    public void setNb_part_max(int nb_part_max) {
        this.nb_part_max = nb_part_max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nb_part_max=" + nb_part_max +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
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
