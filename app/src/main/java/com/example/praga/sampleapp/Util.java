package com.example.praga.sampleapp;

/**
 * Created by abpriyad on 7/28/2016.
 */
public class Util {

    public static int getPhotoIdAccordingToCatgory(String category)
    {
        switch(category)
        {
            case "Food":
                return R.drawable.cookfood;

            case "Cleanliness":
                return R.drawable.cleanliness;

            case "Girls And Women":
                return R.drawable.girlchild;

            case "Kids":
                return R.drawable.sri;

            default:
                return R.drawable.socialcause;
        }
    }
}
