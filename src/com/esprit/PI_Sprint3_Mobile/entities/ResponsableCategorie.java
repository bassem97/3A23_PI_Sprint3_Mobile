package com.esprit.PI_Sprint3_Mobile.entities;


public class ResponsableCategorie {
    private int id ;
    private String nom;
    private String prenom;
    private String email;
    private Categorie categorie;

    public ResponsableCategorie(int id, String nom, String prenom, String email, Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.categorie = categorie;
    }

    public ResponsableCategorie() {

    }

    public ResponsableCategorie(int id, String nom, String prenom, String email) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "ResponsableCategorie{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", categorie=" + categorie + '}';
    }
}
