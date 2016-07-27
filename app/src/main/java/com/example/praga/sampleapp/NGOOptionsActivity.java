package com.example.praga.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class NGOOptionsActivity extends AppCompatActivity {

    private Button addEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngo_options_page);

        addEventButton = (Button)findViewById(R.id.ngo_options_add_event);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startAddEventIntent = new Intent(getApplicationContext(), AddEventDetailsActivity.class);
                startActivity(startAddEventIntent);
            }
        });
    }
}
