/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.PI_Sprint3_Mobile.entities;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.time.LocalDateTime;

/**
 *
 * @author badreddine
 */
public class Article {
    
    private int id;
    private String titre,corps, image;
    private LocalDateTime date;
    private Commentaire commentaire;
    private Categorie id_categorie;
    private Event event;
    private ImageView imageView;
    public int likes,vues;

    public Article() {
        this.likes = likes;
        this.vues = vues;
        imageView = new ImageView();
    }

  
     public Article(int id,String titre, String corps, String image, LocalDateTime date) {
        this.id=id;
        this.titre = titre;
        this.corps = corps;
        this.image = image;
        this.date = date;
    }
     
 
    public Article(String titre, String corps, String image, LocalDateTime date, int likes, int vues) {
        this.titre = titre;
        this.corps = corps;
        this.image = image;
        this.date = date;
        this.likes = likes;
        this.vues = vues;
    }

    public Article(String titre, String corps, String image, LocalDateTime date, Commentaire commentaire, Event event, ImageView imageView, int likes, int vues) {
        this.titre = titre;
        this.corps = corps;
        this.image = image;
        this.date = date;
        this.commentaire = commentaire;
        this.event = event;
        this.imageView = imageView;
        this.likes = likes;
        this.vues = vues;
    }

    public Article(String titre, String corps, String image, LocalDateTime date, Commentaire commentaire, Categorie id_categorie, Event event, ImageView imageView, int likes, int vues) {
        this.titre = titre;
        this.corps = corps;
        this.image = image;
        this.date = date;
        this.commentaire = commentaire;
        this.id_categorie = id_categorie;
        this.event = event;
        this.imageView = imageView;
        this.likes = likes;
        this.vues = vues;
    }
   
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
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

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public Categorie getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(Categorie id_categorie) {
        this.id_categorie = id_categorie;
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getVues() {
        return vues;
    }

    public void setVues(int vues) {
        this.vues = vues;
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

    public void setTitre(TextField tftitre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
