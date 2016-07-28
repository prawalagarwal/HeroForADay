package com.example.praga.sampleapp;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class NGODescriptionPageActivity extends AppCompatActivity {

    //List<NGODetails> ngoList;
    private String ngoId;
    private int photoId;
    private String eventName;
    private String eventDate;
    private String eventLocation;
    TextView event_name_text;
    TextView event_time_text;
    TextView event_location_text;
    TextView ngo_name;
    TextView ngo_description;
    ImageView ngoDetailImage;

    private MobileServiceClient mClient;
    private MobileServiceTable<NGOV5> mNGOTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodescription_page);
        //InitializeData();
        Bundle extra = getIntent().getExtras();
        if (extra != null)
        {
            ngoId = extra.getString("NGOID");
            photoId = extra.getInt("PHOTOID");
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

        try {
            mClient = new MobileServiceClient(
                    "https://abpriyad.azurewebsites.net",
                    this);

            mNGOTable = mClient.getTable(NGOV5.class);

            Toast.makeText(getApplicationContext(), "Loading! Please Wait...", Toast.LENGTH_SHORT).show();
            FetchNgoDataFromTable();
        } catch (MalformedURLException e) {
            //createAndShowDialog(new Exception("Please verify if device is having internet connectivity"), "Error");
        } catch (Exception e){
            //createAndShowDialog(e, "Error");
        }
    }

    private void InitializeData() {
        /*ngoList = new ArrayList<NGODetails>();
        ngoList.add(new NGODetails("Youngistaan Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cleanliness));
        ngoList.add(new NGODetails("CRY", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.sri));
        ngoList.add(new NGODetails("MAD Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.socialcause));
        ngoList.add(new NGODetails("AkshayPatra Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cookfood));
        ngoList.add(new NGODetails("NanhiKali Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.girlchild));
        ngoList.add(new NGODetails("Youngistaan Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cleanliness));
        ngoList.add(new NGODetails("CRY", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.sri));
        ngoList.add(new NGODetails("MAD Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.socialcause));
        ngoList.add(new NGODetails("AkshayPatra Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.cookfood));
        ngoList.add(new NGODetails("NanhiKali Foundation", "An organisation which takes the help of youth to achieve great stuff!", R.drawable.girlchild));*/
    }

    private void FetchNgoDataFromTable() {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final NGOV5 ngo = mNGOTable.lookUp(ngoId).get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ngo_name.setText(ngo.Name);
                            ngo_description.setText("Contact: " + ngo.contact);
                            ngoDetailImage.setImageResource(photoId);
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
