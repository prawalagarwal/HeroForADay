package com.example.praga.sampleapp;

/**
 * Created by praga on 7/26/2016.
 */
public class NGODetails {
    String ngoName;
    String ngoDescription;
    int photoId;

    NGODetails(String name, String description, int photoId)
    {
        this.ngoName = name;
        this.ngoDescription = description;
        this.photoId = photoId;
    }
}
