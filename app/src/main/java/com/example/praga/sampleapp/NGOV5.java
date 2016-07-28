package com.example.praga.sampleapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abpriyad on 7/27/2016.
 */
public class NGOV5 {
    @SerializedName("id")
    public String Id;

    @SerializedName("name")
    public String Name;

    @SerializedName("address")
    public String Address;

    @SerializedName("contact")
    public String contact;

    @SerializedName("displaypic")
    public byte[] DisplayPic;
}
