package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Timestamp;
import java.util.ArrayList;

import hr.fer.opp.onedayjob.FeedAdapter;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;

public class MainActivity extends AppCompatActivity {

    private ListView listJobs;

    @Override
    protected void onStart() {
        super.onStart();
        listJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Animate the background color of clicked Item
                ColorDrawable[] color = {
                        new ColorDrawable(Color.parseColor("#ffffff")),
                        new ColorDrawable(Color.parseColor("#efeded"))
                };
                TransitionDrawable trans = new TransitionDrawable(color);
                view.setBackground(trans);
                trans.startTransition(2000); // duration 2 seconds

                // Go back to the default background color of Item
                ColorDrawable[] color2 = {
                        new ColorDrawable(Color.parseColor("#efeded")),
                        new ColorDrawable(Color.parseColor("#ffffff"))
                };
                TransitionDrawable trans2 = new TransitionDrawable(color2);
                view.setBackground(trans2);
                trans2.startTransition(2000); // duration 2 seconds
                //goToJob(position);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button filterButton = (Button) findViewById(R.id.filter_button);
        Button jobManagementButton = (Button) findViewById(R.id.job_management_button);
        Button gpsButton = (Button) findViewById(R.id.gps_button);
        Button profileButton = (Button) findViewById(R.id.profile_button);
        Button mailboxButton = (Button) findViewById(R.id.mailbox_button);
        Button randomUserButton = (Button) findViewById(R.id.profile_view_buttton);
        Button main = (Button) findViewById(R.id.main2);

        listJobs = (ListView) findViewById(R.id.list_jobs);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilter();
            }
        });
        jobManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openJobManagement();
            }
        });
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGps();
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });
        mailboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMailbox();
            }
        });
        randomUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUser();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        generateData();
    }

    private void generateData() {
        //Retrofit poziv, bla bla
        //stvaranje i punjenje ove arrayListe koja je treci argument sa stvarnim podacima
        final ArrayList<Posao> jobs = new ArrayList<Posao>();
        jobs.add(new Posao("Drkanje u parku", Timestamp.valueOf("2011-10-02 18:48:05"), "Bas super posao vam je to"));
        jobs.add(new Posao("Smrkanje u parku", Timestamp.valueOf("2013-11-12 4:26:56"), "Mozda jos i bolji"));
        jobs.add(new Posao("Krkanje u parku", Timestamp.valueOf("2017-03-03 12:11:12"), "Najbolji"));
        FeedAdapter feedAdapter = new FeedAdapter(MainActivity.this, R.layout.list_element, jobs);
        listJobs.setAdapter(feedAdapter);
    }

    private void openMain(){
        Intent intent = new Intent(MainActivity.this, TheMainActivity.class);
        startActivity(intent);
    }

    private void openUser(){
        Intent intent = new Intent(MainActivity.this, ProfileViewActivity.class);
        startActivity(intent);
    }

    private void openFilter(){
        Intent intent = new Intent(MainActivity.this, FilterActivity.class);
        startActivity(intent);
    }

    private void openJobManagement(){
        Intent intent = new Intent(MainActivity.this, JobManagementActivity.class);
        startActivity(intent);
    }

    private void openGps(){
        Intent intent = new Intent(MainActivity.this, GpsActivity.class);
        startActivity(intent);
    }

    private void openProfile(){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openMailbox(){
        Intent intent = new Intent(MainActivity.this, MailboxActivity.class);
        startActivity(intent);
    }
}
