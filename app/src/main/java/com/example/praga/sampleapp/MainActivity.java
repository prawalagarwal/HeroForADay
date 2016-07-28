package com.example.praga.sampleapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import android.app.AlertDialog;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

public class MainActivity extends AppCompatActivity implements RVAdapter.ClickListener {

    private List<NGOEvent> ngoEvents;
    private RecyclerView rv;
    //Button menuNGO;

    private MobileServiceClient mClient;
    private MobileServiceTable<EventV5> mEventTable;

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

        ngoEvents = new ArrayList<>();

        try {
            mClient = new MobileServiceClient(
                    "https://abpriyad.azurewebsites.net",
                    this);

            mEventTable = mClient.getTable(EventV5.class);

            Toast.makeText(getApplicationContext(), "Loading! Please Wait...", Toast.LENGTH_LONG).show();
            FetchEventDataFromTable();
        } catch (MalformedURLException e) {
            //createAndShowDialog(new Exception("Please verify if device is having internet connectivity"), "Error");
        } catch (Exception e){
            //createAndShowDialog(e, "Error");
        }
    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(this, ngoEvents);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);
    }

    private void initializeData(List<EventV5> events) {
        for (int i = 0; i < events.size(); ++i)
        {
            EventV5 event = events.get(i);
            int photoId = Util.getPhotoIdAccordingToCatgory(event.Category);

            String formattedDate = event.StartDateTime;
            try {
                DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy HH : mm a", Locale.ENGLISH);
                DateFormat targetFormat = new SimpleDateFormat("MMM dd");
                Date date = originalFormat.parse(event.StartDateTime);
                formattedDate = targetFormat.format(date);
            }
            catch (ParseException p){
            }
            ngoEvents.add(new NGOEvent(event.EventName, formattedDate + " for " + event.Duration + (event.Duration.equals("1") ? " hr." : " hrs."), event.Location, photoId, event.NgoId));
        }

        /*ngoEvents.add(new NGOEvent("Cleanliness Drive", "This Thursday 3 hours", "Gachibowli", R.drawable.cleanliness));
        ngoEvents.add(new NGOEvent("Teach A Kid", "This Thursday 2 hours", "Madhapur", R.drawable.sri));
        ngoEvents.add(new NGOEvent("Paint A Public Wall", "Next Tuesday 2 hours", "HiTechCity", R.drawable.socialcause));
        ngoEvents.add(new NGOEvent("Cook For the Needy", "This Thursday 3 hours", "Jubilee Hills", R.drawable.cookfood));
        ngoEvents.add(new NGOEvent("Adopt A Girl Child", "This Thursday 3 hours", "Rethibowli", R.drawable.girlchild));
        ngoEvents.add(new NGOEvent("Cleanliness Drive", "This Thursday 3 hours", "Gachibowli", R.drawable.cleanliness));
        ngoEvents.add(new NGOEvent("Teach A Kid", "This Thursday 2 hours", "Madhapur", R.drawable.sri));
        ngoEvents.add(new NGOEvent("Paint A Public Wall", "Next Tuesday 2 hours", "HiTechCity", R.drawable.socialcause));
        ngoEvents.add(new NGOEvent("Cook For the Needy", "This Thursday 3 hours", "Jubilee", R.drawable.cookfood));
        ngoEvents.add(new NGOEvent("Adopt A Girl Child", "This Thursday 3 hours", "Rethibowli", R.drawable.girlchild));*/
    }

    @Override
    public void itemCLicked(View view, int position) {
        Intent descriptionStartIntent = new Intent(this, NGODescriptionPageActivity.class);
        //Intent descriptionStartIntent = new Intent(this, AddEventDetailsActivity.class);
        //Intent descriptionStartIntent = new Intent(this, NGOOptionsActivity.class);
        descriptionStartIntent.putExtra("NGOID", ngoEvents.get(position).ngoId);
        descriptionStartIntent.putExtra("PHOTOID", ngoEvents.get(position).photoId);
        descriptionStartIntent.putExtra("EVENTNAME", ngoEvents.get(position).eventName);
        descriptionStartIntent.putExtra("EVENTTIME", ngoEvents.get(position).date);
        descriptionStartIntent.putExtra("EVENTLOCATION", ngoEvents.get(position).location);
        startActivityForResult(descriptionStartIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 8 && resultCode == RESULT_OK){
            Toast.makeText(getApplicationContext(), "Loading! Please Wait...", Toast.LENGTH_LONG).show();
            FetchEventDataFromTable();
        }
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
                startActivityForResult(intent, 8);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void FetchEventDataFromTable() {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final List<EventV5> events = mEventTable.execute().get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initializeData(events);
                            initializeAdapter();
                        }
                    });
                }
                catch (Exception e)
                {
                    //createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        runAsyncTask(task);
    }

    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                return task.execute();
            }
    }

    /*private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }

    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }

    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }*/
}
