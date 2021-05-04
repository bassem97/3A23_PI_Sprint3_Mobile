package com.esprit.PI_Sprint3_Mobile.entities;

import java.time.LocalDateTime;

public class Participant {

    private int id;
    private Event event;
    private User user;
    private String avis;
    private LocalDateTime dateParticipation;
    private int daysReminder;

    public Participant() {
        this.dateParticipation = LocalDateTime.now();
        this.daysReminder = 1;
    }

    public Participant(Event event, User user) {
        this.dateParticipation = LocalDateTime.now();
        this.daysReminder = 1;
        this.event = event;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public LocalDateTime getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(LocalDateTime dateParticipation) {
        this.dateParticipation = dateParticipation;
    }

    public int getDaysReminder() {
        return daysReminder;
    }

    public void setDaysReminder(int daysReminder) {
        this.daysReminder = daysReminder;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", event=" + event +
                ", user=" + user +
                ", avis='" + avis + '\'' +
                ", dateParticipation=" + dateParticipation +
                ", daysReminder=" + daysReminder +
                '}';
    }
}
