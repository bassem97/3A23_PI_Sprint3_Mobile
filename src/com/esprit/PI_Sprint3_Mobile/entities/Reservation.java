package com.esprit.PI_Sprint3_Mobile.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private int id ;
    private Categorie categorie;
    private LocalDate date ;
    private LocalTime temps;

    public Reservation(int id, Categorie categorie , LocalDate date , LocalTime temps) {
        this.id = id;
        this.categorie = categorie;
        this.date=date ;
        this.temps=temps;
    }

    public Reservation() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTemps() {
        return temps;
    }

    public void setTemps(LocalTime temps) {
        this.temps = temps;
    }

    @Override
    public String toString() {
        return "ResponsableCategorie{" + "id=" + id + ", categorie=" + categorie + ", date=" + date + ", temps =" + temps + '}';
    }
}
