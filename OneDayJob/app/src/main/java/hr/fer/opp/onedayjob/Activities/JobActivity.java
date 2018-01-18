package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.Servisi.KorisnikServis;
import hr.fer.opp.onedayjob.Servisi.PosaoServis;
import hr.fer.opp.onedayjob.util.Util;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JobActivity extends AppCompatActivity {

    @BindView(R.id.header_cover_image)
    ImageView slikaKategorijePosla;

    @BindView(R.id.naziv_kategorije)
    TextView nazivKategorijePosla;

    @BindView(R.id.opis_posla)
    EditText opisPosla;

    @BindView(R.id.vrijeme_posla)
    EditText vrijemePosla;

    @BindView(R.id.naslov_posla)
    EditText naslovPosla;


    @BindView(R.id.posao_trajanje)
    EditText trajanjePosla;

    @BindView(R.id.posao_zarada)
    EditText zaradaPosla;

    @BindView(R.id.posao_lokacija)
    EditText lokacijaPosla;

    @BindView(R.id.posao_poslodavac)
    EditText poslodavacPosla;

    @BindView(R.id.gumb_izmijeni_posao)
    Button izmijeniPosao;

    @BindView(R.id.gumb_izbrisi_posao)
    Button izbrisiPosao;

    Posao trenutniPosao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        ButterKnife.bind(this);


        if(false){ // ne može mijenjati ako nije vlasnik posla
            onemoguciIzmijene();
        }

        if(false){ // ako je vlasnik
            izmijeniPosao.setVisibility(View.VISIBLE);
        }

        if(false){ // samo ako je admin
            izmijeniPosao.setVisibility(View.VISIBLE);
            izbrisiPosao.setVisibility(View.VISIBLE);
        }

        trenutniPosao  = (Posao) getIntent().getExtras().get("odabraniPosao");
        popuniZaPosao(trenutniPosao);
    }

    private void popuniZaPosao(Posao posao) {
        Log.d("posao aktivnost", "popuniZaPosao: " + posao + "koji ima kategoriju " + posao.getKategorijaID());

        String opis = posao.getOpis();
        String naslov = posao.getNaslov();
        String lokacija = posao.getLokacija();
        Integer zarada = posao.getPonudeniNovac();
        Long trajanje = posao.getTrajanje();
        Long poslodavac = posao.getPoslodavacId();
        String vrijeme = new Date(posao.getVrijeme()).toString();


        Kategorija2 prikazanaKategorija = Kategorija2.dajKategoriju(posao.getKategorijaID());
        Log.d("JobAct", "popuniZaPosao: prikazujem" + prikazanaKategorija);
        String nazivKategorije = prikazanaKategorija.getIme();

        nazivKategorijePosla.setText(nazivKategorije);
        opisPosla.setText(opis);
        trajanjePosla.setText(trajanje.toString());
        zaradaPosla.setText(zarada.toString());
        poslodavacPosla.setText(poslodavac.toString()); // Dohvat baš imena
        lokacijaPosla.setText(lokacija);
        naslovPosla.setText(naslov);
        vrijemePosla.setText(vrijeme);

//        slikaKategorijePosla.setBackgroundResource(Util.slikaZa(kategorije));
        slikaKategorijePosla.setImageResource(prikazanaKategorija.getSlikaID());
    }

    public void izbrisiPosao(View view){
        // Poziv na bazu za brisanjem posla
        Log.d("JobActivity", "izbrisiPosao: " + trenutniPosao.toString());
    }

    public void izmijeniPosao(View view){
        // Poziv na bazu za izmjenom posla
        Log.d("JobActivity", "izmijeniPosao: moram izmjeniti" + trenutniPosao.toString());

        // Nisam uspio izmijenit posao!!

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
                service.updatePosao(trenutniPosao).enqueue(new Callback<Posao>() {

                    @Override
                    public void onResponse(Call<Posao> call, Response<Posao> response) {
                        Log.d("Login", "onResponse: " + response.body());

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("korisnik", trenutniPosao); //OVDJE TREBA NOVI KORISNIK
                        Intent intent = new Intent(JobActivity.this, TheMainActivity.class);
                        intent.putExtras(bundle);
                        Toast.makeText(JobActivity.this, "Promjena prihvaćena",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Posao> call, Throwable t) {
                        Log.d("Login", "onFailure: " + t.getMessage());
                        Toast.makeText(JobActivity.this, "Nisam uspio spremiti podatke", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).run();
    }


    private void onemoguciIzmijene(){
        lokacijaPosla.setKeyListener(null);
        opisPosla.setKeyListener(null);
        zaradaPosla.setKeyListener(null);
        trajanjePosla.setKeyListener(null);
        naslovPosla.setKeyListener(null);
        poslodavacPosla.setKeyListener(null);
        vrijemePosla.setKeyListener(null);
    }

}
