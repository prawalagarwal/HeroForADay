package com.example.praga.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RVAdapter.ClickListener {

    private List<NGOEvent> ngoEvents;
    private RecyclerView rv;
    //Button menuNGO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        rv = (RecyclerView) findViewById(R.id.rv);

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
        Intent descriptionStartIntent = new Intent(this, NGODescriptionPageActivity.class);
        //Intent descriptionStartIntent = new Intent(this, AddEventDetailsActivity.class);
        //Intent descriptionStartIntent = new Intent(this, NGOOptionsActivity.class);
        descriptionStartIntent.putExtra("INDEX", position);
        descriptionStartIntent.putExtra("EVENTNAME", ngoEvents.get(position).eventName);
        descriptionStartIntent.putExtra("EVENTTIME", ngoEvents.get(position).date);
        descriptionStartIntent.putExtra("EVENTLOCATION", ngoEvents.get(position).location);
        startActivity(descriptionStartIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_ngo_flow:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(this, AddEventDetailsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
