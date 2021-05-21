/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.PI_Sprint3_Mobile.entities;

import java.time.LocalDateTime;

public class Commentaire {
    
    private int ref;
    private String corps;
    private int rating;
    private LocalDateTime date;
    private User user;
    private Article article;
  
    public Commentaire() {
        
    }

    public Commentaire(String corps, int rating, LocalDateTime date) {
        this.corps = corps;
        this.rating = rating;
        this.date = date;
    }
     

    public Commentaire(int ref, String corps, LocalDateTime date, User user, Article article) {
        this.ref = ref;
        this.corps = corps;
        this.date = date;
        this.user = user;
        this.article = article;
    }

    public Commentaire(int ref, String corps, LocalDateTime date, Article article) {
        this.ref = ref;
        this.corps = corps;
        this.date = date;
        this.article = article;
    }

    public Commentaire(Article article) {
        this.article = article;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
