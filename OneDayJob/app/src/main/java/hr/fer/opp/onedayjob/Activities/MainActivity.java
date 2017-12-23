package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import hr.fer.opp.onedayjob.R;

/* DEPRICATED */

public class MainActivity extends AppCompatActivity {

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
