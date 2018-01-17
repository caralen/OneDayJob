package hr.fer.opp.onedayjob.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

import hr.fer.opp.onedayjob.FeedAdapter;
import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;

public class TheMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,AdapterView.OnItemClickListener {

    //TODO : izbrisati MapsActivity(class) i GpsActivity(class) vjv nepotrebni

    private static final List<Posao> posloviTest = new ArrayList<>();

    /* GPS vars*/
    private GoogleMap mMap;
    List<Address> results = new ArrayList<Address>();
    Geocoder geocoder;
    LatLng fokus = null;
    /* GPS vars*/


    /*MailBox*/
    ListView usersListV;

    int[] slike={R.drawable.user1, R.drawable.instrukcije, R.drawable.user1,R.drawable.user1,R.drawable.user1, R.drawable.user1, R.drawable.user1, R.drawable.user1};
    String[] messages={"bok", "posao?", "DAAAA >3Sta ima lima?"};
    String[] users= {"Ivan Ivanic", "Luka Lukic", "Marija Marijanovic", "Pero Peric", "Pernica Petricic", "Ana Anicic", "lalal", "TOni"};

    /*MailBox*/


    static{
        posloviTest.add(new Posao(1, 1, 1, "Čišćenje snijega","Bas super posao vam je to!", "Branimirova 15, Zagreb",  Timestamp.valueOf("2011-10-02 18:00:00").getTime(), 120, 80, false,  Arrays.asList(new Long[]{Kategorija2.FIZICKI_POSAO.getId()}), false));
        posloviTest.add(new Posao(2, 2, 2, "Pranje auta","Treba mi oprati moj novi audi R8, masnu lovu placam.", "Ilica 125, Zagreb",  Timestamp.valueOf("2011-12-22 19:00:00").getTime(), 60, 150, false, Arrays.asList(new Long[]{Kategorija2.CISCENJE.getId()}), false));
        posloviTest.add(new Posao(3, 3, 3, "Hranjenje ljubimaca","Idem na put i treba mi nahraniti sve moje ljubimce, a pošto imam doma cijeli zoološki vrt trebat će vam vremena da to napravite.", "Vukovarska 30, Zagreb",  Timestamp.valueOf("2011-01-20 10:00:00").getTime(), 90, 60, false,  Arrays.asList(new Long[]{Kategorija2.CUVANJE_ZIVOTINJE.getId()}), false));
    }

    //layout_FEED
    ListView listJobs;
    RelativeLayout feedLayout;
    BottomNavigationView navigation;
    //layout_gps
    RelativeLayout gpsLayout;
    Button gpsButton;
    //layout_chat
    Button mailbox;
    RelativeLayout mailLayout;

    // NAV_HEADER_ELEMENTS
    ImageView header_profilePhoto;
    TextView header_firstAndLastName;
    TextView header_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geocoder = new Geocoder(this, Locale.getDefault());
        setContentView(R.layout.activity_the_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ------------ GPS setup -------- */
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        /* ------------ GPS setup -------- */

        //FEED LAYOUT
        feedLayout = (RelativeLayout) findViewById(R.id.navigation_feed);
        listJobs = (ListView) findViewById(R.id.list_jobs);

        //GPS LAYOUT
        gpsLayout = (RelativeLayout) findViewById(R.id.navigation_gps);
        gpsButton = (Button)findViewById(R.id.navigation_gps_button);

        //MAILBOX LAYOUT
        mailLayout = (RelativeLayout) findViewById(R.id.navigation_mail);
        mailbox = (Button)findViewById(R.id.navigation_mail_button);


        /*-- NAV_HEADER_THE_MAIN elements TODO --*/
        //header_profilePhoto = (ImageView)findViewById(R.id.nav_header_profilePhoto);
        //header_firstAndLastName = (TextView)findViewById(R.id.nav_header_name_and_lastname);
        //header_email = (TextView)findViewById(R.id.nav_header_email);

        //loadUserData();
        /*-- NAV_HEADER_THE_MAIN elements TODO --*/




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




        /*-------------------------------------------- MAILBOX ---------------------------------------------------------------- */
        new JsonTask().execute("https://onedayjobapp2.azurewebsites.net/poruke?korisnikID1=2&&korisnikID2=3");
        //Button chatButton = (Button) findViewById(R.id.chat_button);

        //chatButton.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View view) {
        //    openChat();
        //}
        //});

        usersListV=(ListView) findViewById(R.id.listV);

        CustomAdapter customAdapter=new CustomAdapter();
        usersListV.setAdapter(customAdapter);

        //ArrayAdapter<String> userAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        //usersListV.setAdapter(userAdapter);
        usersListV.setOnItemClickListener(this);
        /*-------------------------------------------- MAILBOX ---------------------------------------------------------------- */




    }

    /*-------------------------------------------- MAILBOX Adapter ---------------------------------------------------------------- */
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return users.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView3);
            TextView textView= (TextView) view.findViewById(R.id.textView_message);

            //userova slika ovisno cija je poruka
            imageView.setImageResource(slike[i]);
            textView.setText(users[i]);


            return view;
        }
    }
    /*-------------------------------------------- MAILBOX Adapter ---------------------------------------------------------------- */

    /*-------------------------------------------- MAILBOX method ---------------------------------------------------------------- */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        String talkingTo=(String)usersListV.getItemAtPosition(position);
        //Toast.makeText(this, talkingTo, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TheMainActivity.this, ChatActivity.class);
        intent.putExtra("takingTo", talkingTo);
        startActivity(intent);
    }
    /*-------------------------------------------- MAILBOX method ---------------------------------------------------------------- */




    @Override
    protected void onResume() {
        super.onResume();
        generateData();
    }



    private void generateData() {
        //Retrofit poziv, bla bla
        //stvaranje i punjenje ove arrayListe koja je treci argument u konstruktoru FeedAdaptera sa stvarnim podacima
        Log.d("poslovi_test", "generateData: " + posloviTest);
        // zasad koristimo dummy podatke
        FeedAdapter feedAdapter = new FeedAdapter(TheMainActivity.this, R.layout.list_element, posloviTest);
        listJobs.setAdapter(feedAdapter);
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
            Intent intent = new Intent(TheMainActivity.this, LoginActivity.class);
            startActivity(intent);
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

    public void openJob(){
        Intent intent = new Intent(TheMainActivity.this, JobActivity.class);
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

    private void loadUserData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Korisnik k = (Korisnik) bundle.get("korisnik");

       //header_email.setText("asdf");

       // header_firstAndLastName.setText(k.getIme() + " " + k.getPrezime());
       // header_email.setText(k.getEmail());

        Toast.makeText(this, "This is my Toast message! "  + header_email,
                Toast.LENGTH_LONG).show();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*TEST*/
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);


        //locationManager.removeUpdates(locationListener);
        /*TEST*/


        for (Posao p : posloviTest){

            //Iz String lokacije izvuci adresu (class Adress)
            try {
                results = geocoder.getFromLocationName(p.getLokacija(),2);
            } catch (IOException e) {
                continue;
            }
            if (results.isEmpty())continue;

            //Daj mi Latitude i Longitude adrese
            LatLng posaoLatLng = new LatLng(results.get(0).getLatitude(),results.get(0).getLongitude());

            //Postavi marker
            Marker posaoMarker = mMap.addMarker(new MarkerOptions().position(posaoLatLng).title(p.getNaslov()));


        }

        /*Lat i Long od fera, tu ce biti pocetni zoom mape*/
        if (fokus == null){
            fokus = new LatLng(45.8007017,15.9690278);
        }

        //LatLng trg = new LatLng(45.813104, 15.977587);
        //LatLng

        // googleMap.setOnInfoWindowClickListener(listener);

        //final Marker m1 = mMap.addMarker(new MarkerOptions().position(trg).title("Ovde jest trg ba"));
        //final Marker m2 = mMap.addMarker(new MarkerOptions().position(fer).title("Ovdje je nas najdrazi faksic"));



        GoogleMap.OnInfoWindowClickListener listener = new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                //Ovo vjv moze bolje - Spajanje markera i posla
                String title = marker.getTitle();

                for (Posao pos : posloviTest){
                    if (title.equals(pos.getNaslov())){

                        Intent intent = new Intent(TheMainActivity.this, JobActivity.class);

                        Bundle bundle = new Bundle();

                        Log.d("odabrani posao", "onClick: saljem posao" + pos.getNaslov());
                        bundle.putSerializable("odabraniPosao", pos);
                        intent.putExtras(bundle);

                        startActivity(intent);


                        //gpsButton.setVisibility(View.VISIBLE);
                        // gpsButton.setText(pos.getPosaoId() + "");



                    }
                }


            }
        };

        mMap.setOnInfoWindowClickListener(listener);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(fokus));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 13.0f ) );
    }


}
