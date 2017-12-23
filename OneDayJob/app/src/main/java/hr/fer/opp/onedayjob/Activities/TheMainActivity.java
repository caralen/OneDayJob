package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hr.fer.opp.onedayjob.R;

public class TheMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //layout_FEED
    RelativeLayout feedLayout;
    BottomNavigationView navigation;
    TextView text;
    Button randomPosao; //TODO
    Button randomUserProfile; //
    //layout_gps
    RelativeLayout gpsLayout;
    Button gpsButton;
    //layout_chat
    Button mailbox;
    RelativeLayout mailLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FEED LAYOUT
        feedLayout = (RelativeLayout) findViewById(R.id.navigation_feed);
        text = (TextView)findViewById(R.id.message);
        randomPosao = (Button)findViewById(R.id.random_Posao);
        randomUserProfile = (Button)findViewById(R.id.random_korisnik);

        //GPS LAYOUT
        gpsLayout = (RelativeLayout) findViewById(R.id.navigation_gps);
        gpsButton = (Button)findViewById(R.id.navigation_gps_button);

        //MAILBOX LAYOUT
        mailLayout = (RelativeLayout) findViewById(R.id.navigation_mail);
        mailbox = (Button)findViewById(R.id.navigation_mail_button);


        randomPosao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJob();
            }
        });
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGps();
            }
        });
        randomUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
        mailbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMailbox();
            }
        });

        /*-------------------------------------------- NAVIGATION BAR ---------------------------------------------------------------- */
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_sel_feed:
                        feedLayout.setVisibility(View.VISIBLE);
                        gpsLayout.setVisibility(View.GONE);
                        mailLayout.setVisibility(View.GONE);

                        return true;
                    case R.id.navigation_sel_gps:
                        feedLayout.setVisibility(View.GONE);
                        gpsLayout.setVisibility(View.VISIBLE);
                        mailLayout.setVisibility(View.GONE);
                        return true;
                    case R.id.navigation_sel_mail:
                        feedLayout.setVisibility(View.GONE);
                        gpsLayout.setVisibility(View.GONE);
                        mailLayout.setVisibility(View.VISIBLE);
                        return true;
                }


                return false;
            }
        });
         /*-------------------------------------------- NAVIGATION BAR ---------------------------------------------------------------- */



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.the_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.filter_item) {
            openFilter();
        } else if (id == R.id.moji_poslovi_item) {
            openJobManagement();
        } else if (id == R.id.dodaj_posao_item) {

        } else if (id == R.id.uredi_profil) {
            editProfile();
        } else if (id == R.id.statistika) {
            openStatistics();
        } else if (id == R.id.odjava) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void openOtherUser(){
        Intent intent = new Intent(TheMainActivity.this, ProfileViewActivity.class);
        startActivity(intent);
    }

    private void openStatistics(){
        Intent intent = new Intent(TheMainActivity.this, StatisticsActivity.class);
        startActivity(intent);
    }

    private void openFilter(){
        Intent intent = new Intent(TheMainActivity.this, FilterActivity.class);
        startActivity(intent);
    }

    private void openJobManagement(){
        Intent intent = new Intent(TheMainActivity.this, JobManagementActivity.class);
        startActivity(intent);
    }

    private void openJob(){
        Intent intent = new Intent(TheMainActivity.this, JobActivity.class);
        startActivity(intent);
    }

    private void openGps(){
        Intent intent = new Intent(TheMainActivity.this, GpsActivity.class);
        startActivity(intent);
    }

    private void openProfile(){
        Intent intent = new Intent(TheMainActivity.this, ProfileViewActivity.class);
        startActivity(intent);
    }

    private void editProfile(){
        Intent intent = new Intent(TheMainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openMailbox(){
        Intent intent = new Intent(TheMainActivity.this, MailboxActivity.class);
        startActivity(intent);
    }



}
