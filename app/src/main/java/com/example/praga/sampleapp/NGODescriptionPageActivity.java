package com.example.praga.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NGODescriptionPageActivity extends AppCompatActivity {

    List<NGODetails> ngoList;
    private int position;
    private String eventName;
    private String eventDate;
    private String eventLocation;
    TextView event_name_text;
    TextView event_time_text;
    TextView event_location_text;
    TextView ngo_name;
    TextView ngo_description;
    ImageView ngoDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodescription_page);
        InitializeData();
        Bundle extra = getIntent().getExtras();
        if (extra != null)
        {
            position = extra.getInt("INDEX");
            eventName = extra.getString("EVENTNAME");
            eventDate = extra.getString("EVENTTIME");
            eventLocation = extra.getString("EVENTLOCATION");
        }

        event_name_text = (TextView)findViewById(R.id.ngo_event_description_name);
        event_time_text = (TextView)findViewById(R.id.ngo_event_description_timings);
        event_location_text = (TextView)findViewById(R.id.ngo_event_description_location);
        ngo_name = (TextView)findViewById(R.id.ngo_page_text_1);
        ngo_description = (TextView) findViewById(R.id.ngo_page_text_2);
        ngoDetailImage = (ImageView) findViewById(R.id.ngo_description_photo);

        event_name_text.setText(eventName);
        event_time_text.setText(eventDate);
        event_location_text.setText(eventLocation);
        ngo_name.setText(ngoList.get(position).ngoName);
        ngo_description.setText(ngoList.get(position).ngoDescription);
        ngoDetailImage.setImageResource(ngoList.get(position).photoId);
    }

    private void InitializeData() {
        ngoList = new ArrayList<NGODetails>();
        ngoList.add(new NGODetails("Youngistaan Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cleanliness));
        ngoList.add(new NGODetails("CRY", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.sri));
        ngoList.add(new NGODetails("MAD Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.socialcause));
        ngoList.add(new NGODetails("AkshayPatra Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cookfood));
        ngoList.add(new NGODetails("NanhiKali Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.girlchild));
        ngoList.add(new NGODetails("Youngistaan Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cleanliness));
        ngoList.add(new NGODetails("CRY", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.sri));
        ngoList.add(new NGODetails("MAD Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.socialcause));
        ngoList.add(new NGODetails("AkshayPatra Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cookfood));
        ngoList.add(new NGODetails("NanhiKali Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.girlchild));
    }
}
