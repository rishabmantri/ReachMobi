package com.example.reachmobi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {
    @SerializedName("results")
    private List<Event> getEvents;

    public List<Event> getEvents() {
         return getEvents;
    }

    public void setEvents(List<Event> getEvents) {
        this.getEvents = getEvents;
    }
}
