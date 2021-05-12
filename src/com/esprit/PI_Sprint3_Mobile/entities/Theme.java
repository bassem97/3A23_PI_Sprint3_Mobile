package com.esprit.PI_Sprint3_Mobile.entities;

public class Theme {
    private int id;
    private String libelle;
    private int vues;

    public Theme() {
    }

    public Theme(int id) {}

    public Theme(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getVues() {
        return vues;
    }

    public void setVues(int vues) {
        this.vues = vues;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", vues='" + vues + '\'' +
                '}';
    }
}
