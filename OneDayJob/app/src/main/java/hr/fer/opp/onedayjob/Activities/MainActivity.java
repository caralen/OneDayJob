package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import hr.fer.opp.onedayjob.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test test
        //Lude nove promjene

        Button filterButton = (Button) findViewById(R.id.filter_button);
        Button jobManagementButton = (Button) findViewById(R.id.job_management_button);
        Button gpsButton = (Button) findViewById(R.id.gps_button);
        Button profileButton = (Button) findViewById(R.id.profile_button);
        Button mailboxButton = (Button) findViewById(R.id.mailbox_button);

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
