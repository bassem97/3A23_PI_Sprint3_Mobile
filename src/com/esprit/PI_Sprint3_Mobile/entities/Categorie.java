package com.esprit.PI_Sprint3_Mobile.entities;

import java.util.Objects;

public class Categorie {
    private int id ;
    private String titre ;
    private String description;


    public Categorie(){
    }
    public Categorie(int id, String titre , String description){
        this.id= id;
        this.titre= titre;
        this.description= description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return titre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return id == categorie.id;
    }
}
