package com.esprit.PI_Sprint3_Mobile.entities;

public class EventSponsor {

    private Event event;
    private Sponsor sponsor;

    public EventSponsor() {
    }

    public EventSponsor(Event event, Sponsor sponsor) {
        this.event = event;
        this.sponsor = sponsor;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    @Override
    public String toString() {
        return sponsor.toString();
    }
}
