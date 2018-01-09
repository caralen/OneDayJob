package hr.fer.opp.onedayjob.Activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Kategorija;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;

public class JobActivity extends AppCompatActivity {

    @BindView(R.id.header_cover_image)
    ImageView slikaKategorijePosla;

    @BindView(R.id.naziv_kategorije)
    TextView nazivKategorijePosla;

    @BindView(R.id.opis_posla)
    TextView opisPosla;

    @BindView(R.id.naslov_posla)
    TextView naslovPosla;


    @BindView(R.id.posao_trajanje)
    TextView trajanjePosla;

    @BindView(R.id.posao_zarada)
    TextView zaradaPosla;

    @BindView(R.id.posao_lokacija)
    TextView lokacijaPosla;

    @BindView(R.id.posao_poslodavac)
    TextView poslodavacPosla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        ButterKnife.bind(this);

        Posao posao = (Posao) getIntent().getExtras().get("odabraniPosao");
        popuniZaPosao(posao);
    }

    private void popuniZaPosao(Posao posao) {
        Log.d("posao aktivnost", "popuniZaPosao: " + posao);

        String opis = posao.getOpis();
        String naslov = posao.getNaslov();
        String lokacija = posao.getLokacija();
        Integer zarada = posao.getPonudeniNovac();
        Long trajanje = posao.getTrajanje();
        String poslodavac = posao.getPoslodavacId();

        List<Kategorija> kategorije = posao.getKategorije();
        Kategorija prikazanaKategorija = kategorije.get(0);
        String nazivKategorije = prikazanaKategorija.getIme();

        nazivKategorijePosla.setText(nazivKategorije);
        opisPosla.setText(opis);
        trajanjePosla.setText(trajanje.toString()+ " min");
        zaradaPosla.setText(zarada.toString()+ " kn");
        poslodavacPosla.setText(poslodavac);
        lokacijaPosla.setText(lokacija);
        naslovPosla.setText(naslov);
    }
}
