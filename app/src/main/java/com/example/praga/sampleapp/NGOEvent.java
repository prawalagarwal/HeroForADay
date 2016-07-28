package com.example.praga.sampleapp;

/**
 * Created by praga on 7/25/2016.
 */
public class NGOEvent {
    String eventName;
    String date;
    String location;
    int photoId;
    String ngoId;

    NGOEvent(String name, String date, String location, int photoId, String ngoId) {
        this.eventName = name;
        this.date = date;
        this.location = location;
        this.photoId = photoId;
        this.ngoId = ngoId;
    }
}
