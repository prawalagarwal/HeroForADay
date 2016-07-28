package com.example.praga.sampleapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abpriyad on 7/27/2016.
 */
public class EventV5 {

    @SerializedName("id")
    public String Id;

    @SerializedName("eventName")
    public String EventName;

    @SerializedName("startDateTime")
    public String StartDateTime;

    @SerializedName("duration")
    public String Duration;

    @SerializedName("location")
    public String Location;

    @SerializedName("category")
    public String Category;

    @SerializedName("ngoId")
    public String NgoId;

    @SerializedName("displayPic")
    public byte[] DisplayPic;
}
