package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Poruka;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.Servisi.KorisnikServis;
import hr.fer.opp.onedayjob.Servisi.PorukaServis;
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

    @BindView(R.id.kontaktiraj_poslodavca)
    Button kontaktirajPoslodavca;


    @BindView(R.id.gumb_unesi_posloprimca)
    Button unesiPosloprimca;




    @BindView(R.id.posloprimac_email)
    EditText posloprimacEmail;




    Korisnik korisnik = TheMainActivity.korisnik;
    Posao trenutniPosao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        ButterKnife.bind(this);
        trenutniPosao = (Posao) getIntent().getExtras().get("odabraniPosao");
        popuniZaPosao(trenutniPosao);

//        korisnik = (Korisnik) getIntent().getExtras().getSerializable("korisnik");
//        Toast.makeText(JobActivity.this, "korisnik"+  korisnik.toString(), Toast.LENGTH_SHORT).show();

        boolean owner = false;
        boolean admin = false;

        if (korisnik.getkorisnikID() == trenutniPosao.getPoslodavacId()){
            kontaktirajPoslodavca.setVisibility(View.GONE);
            owner=true;
        }



        if (!owner) { // ne može mijenjati ako nije vlasnik posla
            onemoguciIzmijene();
        }

        if (owner) { // ako je vlasnik
            izmijeniPosao.setVisibility(View.VISIBLE);
        }

        if (admin) { // samo ako je admin
            izbrisiPosao.setVisibility(View.VISIBLE);
        }

        izbrisiPosao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO IZBRISI - BAZA
            }
        });

        izmijeniPosao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opisPoslaNew=opisPosla.getText().toString();
                String naslovPosla;

            }
        });

        unesiPosloprimca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //TODO provjeri da li postoji u bazi user s mailom:
                String posloprimacMail = posloprimacEmail.getText().toString();



                Toast.makeText(JobActivity.this,"Posao dodjeljen",Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable("korisnik", korisnik);
                Intent intent = new Intent(JobActivity.this, TheMainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });




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

    public void izbrisiPosao(View view) {
        // Poziv na bazu za brisanjem posla
        Log.d("JobActivity", "izbrisiPosao: " + trenutniPosao.toString());
    }

    public void izmijeniPosao(View view) {
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


                        Intent intent = new Intent(JobActivity.this, TheMainActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("korisnik", korisnik); //OVDJE TREBA NOVI KORISNIK
                        intent.putExtras(bundle);
                        Toast.makeText(JobActivity.this, "Promjena prihvaćena", Toast.LENGTH_LONG).show();
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


    private void onemoguciIzmijene() {
        lokacijaPosla.setKeyListener(null);
        opisPosla.setKeyListener(null);
        zaradaPosla.setKeyListener(null);
        trajanjePosla.setKeyListener(null);
        naslovPosla.setKeyListener(null);
        poslodavacPosla.setKeyListener(null);
        vrijemePosla.setKeyListener(null);

        posloprimacEmail.setVisibility(View.GONE);
        izbrisiPosao.setVisibility(View.GONE);
        izmijeniPosao.setVisibility(View.GONE);
        unesiPosloprimca.setVisibility(View.GONE);

    }

    public void kontaktiraj(View view) {

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
//        final KorisnikServis service = retrofit.create(KorisnikServis.class);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                service.getKorisnik("korisnik/"+korisnik2Id+"/detalji").enqueue(new Callback<Korisnik>() {
//                    @Override
//                    public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
//                        Log.d("Login", "onResponse: " + response.body());
//                        korisnik2 = response.body();
//                        user[1] = korisnik2.getIme();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Korisnik> call, Throwable t) {
//                        Log.d("Login", "onFailure: " + t.getMessage());
//                    }
//                });
//            }
//        }).run();


        final PorukaServis pservice = retrofit.create(PorukaServis.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                pservice.getPoruke(korisnik.getKorisnikID(), trenutniPosao.getPoslodavacId()).enqueue(new Callback<List<Poruka>>() {
                    @Override
                    public void onResponse(Call<List<Poruka>> call, Response<List<Poruka>> response) {
                        Log.d("Login", "onResponse: " + response.body());
                        List<Poruka> poruke = response.body();
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(JobActivity.this, ChatActivity.class);
                        intent.putExtra("poruke", (Serializable) poruke);
                        bundle.putLong("poslodavac", trenutniPosao.getPoslodavacId());

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<List<Poruka>> call, Throwable t) {
                        Log.d("Login", "onFailure: " + t.getMessage());
                    }
                });
            }
        }).run();
    }

}
