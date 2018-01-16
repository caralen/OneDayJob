package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.Servisi.KorisnikServis;
import hr.fer.opp.onedayjob.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerificationActivity extends AppCompatActivity {

    private boolean fillTestCode = true;

    @BindView(R.id.verification_code)
    EditText verificationCode;
Korisnik noviKorisnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);

        if(fillTestCode){
            verificationCode.setText("3418");
        }
    }
    public void attemptVerification(View view) throws IOException, InterruptedException {

        noviKorisnik = (Korisnik) getIntent().getExtras().get("noviKorisnik");
        Integer userGivenCode = Integer.parseInt(verificationCode.getText().toString());
        int expected = Util.calculateVerificationHash(noviKorisnik.getEmail());

        if(!userGivenCode.equals(expected)){
            Toast.makeText(this, "Kod nije odgovarajuć! Očekivani kod je " + expected, Toast.LENGTH_SHORT).show();
            return;
        }
        noviKorisnik.setJeValidiran(true);
        noviKorisnik.setkorisnikID(0);
        Log.d("VERIFICATION", "Upravo sam verificirao: " + noviKorisnik);
        //Spremi ga u bazu

        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://onedayjobapp2.azurewebsites.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final KorisnikServis service = retrofit.create(KorisnikServis.class);

                service.registerKorisnik(noviKorisnik).enqueue(new Callback<Korisnik>() {
                    @Override
                    public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                        Toast.makeText(VerificationActivity.this, "Registrirao sam korisnika!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Korisnik> call, Throwable t) {
                        Log.d("REG", "onFailure: "+ t.getMessage());
                        Toast.makeText(VerificationActivity.this, "Nisam uspio registrirati. Razlog: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }).run();
            }


}
