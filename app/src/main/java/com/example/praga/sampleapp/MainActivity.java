package com.example.praga.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RVAdapter.ClickListener{

    private List<NGOEvent> ngoEvents;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(this, ngoEvents);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);
    }

    private void initializeData() {
        ngoEvents = new ArrayList<>();
        ngoEvents.add(new NGOEvent("Cleanliness Drive", "This Thursday 3 hours", "Gachibowli", R.drawable.cleanliness));
        ngoEvents.add(new NGOEvent("Teach A Kid", "This Thursday 2 hours", "Madhapur", R.drawable.sri));
        ngoEvents.add(new NGOEvent("Paint A Public Wall", "Next Tuesday 2 hours", "HiTechCity", R.drawable.socialcause));
        ngoEvents.add(new NGOEvent("Cook For the Needy", "This Thursday 3 hours", "Jubilee Hills", R.drawable.cookfood));
        ngoEvents.add(new NGOEvent("Adopt A Girl Child", "This Thursday 3 hours", "Rethibowli", R.drawable.girlchild));
        ngoEvents.add(new NGOEvent("Cleanliness Drive", "This Thursday 3 hours", "Gachibowli", R.drawable.cleanliness));
        ngoEvents.add(new NGOEvent("Teach A Kid", "This Thursday 2 hours", "Madhapur", R.drawable.sri));
        ngoEvents.add(new NGOEvent("Paint A Public Wall", "Next Tuesday 2 hours", "HiTechCity", R.drawable.socialcause));
        ngoEvents.add(new NGOEvent("Cook For the Needy", "This Thursday 3 hours", "Jubilee", R.drawable.cookfood));
        ngoEvents.add(new NGOEvent("Adopt A Girl Child", "This Thursday 3 hours", "Rethibowli", R.drawable.girlchild));
    }

    @Override
    public void itemCLicked(View view, int position) {
        //Intent descriptionStartIntent = new Intent(this, NGODescriptionPageActivity.class);
        Intent descriptionStartIntent = new Intent(this, AddEventDetailsActivity.class);
        //descriptionStartIntent.putExtra("INDEX", position);
        //descriptionStartIntent.putExtra("EVENTNAME", ngoEvents.get(position).eventName);
        //descriptionStartIntent.putExtra("EVENTTIME", ngoEvents.get(position).date);
        //descriptionStartIntent.putExtra("EVENTLOCATION", ngoEvents.get(position).location);
        startActivity(descriptionStartIntent);
    }
}
