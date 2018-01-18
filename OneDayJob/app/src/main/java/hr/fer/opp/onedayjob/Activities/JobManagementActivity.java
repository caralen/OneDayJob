package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.ls.LSException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import hr.fer.opp.onedayjob.FeedAdapter;
import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.Servisi.PosaoServis;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JobManagementActivity extends AppCompatActivity {

    private static List<Posao> sviPoslovi = new ArrayList<>();
    private static List<Posao> mojiPoslovi = new ArrayList<>();

    private ListView listMyJobs;
    Korisnik korisnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_management);

        listMyJobs = (ListView) findViewById(R.id.list_my_jobs);

        loadUserData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        generateData();
    }

    private void generateData() {
        Log.d("poslovi_test", "generateData: " + mojiPoslovi);

        // Logging ...
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://onedayjobapp2.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(httpClient.build())
                .build();
        final PosaoServis service = retrofit.create(PosaoServis.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.getAktivniPoslovi().enqueue(new Callback<List<Posao>>() {
                    @Override
                    public void onResponse(Call<List<Posao>> call, Response<List<Posao>> response) {
                        Log.d("Login", "onResponse: " + response.body());
                        mojiPoslovi.clear();
                        sviPoslovi = response.body();
                        for(Posao posao: sviPoslovi){
                            if(posao.getPoslodavacId() == korisnik.getkorisnikID()){
                                mojiPoslovi.add(posao);
                            }
                        }

                        FeedAdapter feedAdapter = new FeedAdapter(JobManagementActivity.this, R.layout.list_element, mojiPoslovi);
                        listMyJobs.setAdapter(feedAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Posao>> call, Throwable t) {
                        Log.d("Login", "onFailure: " + t.getMessage());

                        Toast.makeText(JobManagementActivity.this, "Nisam dohvatio iz baze!", Toast.LENGTH_SHORT).show();
                        FeedAdapter feedAdapter = new FeedAdapter(JobManagementActivity.this, R.layout.list_element, mojiPoslovi);
                        listMyJobs.setAdapter(feedAdapter);
                    }
                });
            }
        }).run();
    }

    private void loadUserData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        korisnik = (Korisnik) bundle.get("korisnik");
    }
}
