package hr.fer.opp.onedayjob.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Kategorija;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.util.Util;

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


        if(true){ // ne može mijenjati ako nije vlasnik posla
            onemoguciIzmijene();
        }

        if(false){ // samo ako je admin
            izmijeniPosao.setVisibility(View.VISIBLE);
            izbrisiPosao.setVisibility(View.VISIBLE);
        }

        trenutniPosao  = (Posao) getIntent().getExtras().get("odabraniPosao");
        popuniZaPosao(trenutniPosao);
    }

    private void popuniZaPosao(Posao posao) {
        Log.d("posao aktivnost", "popuniZaPosao: " + posao);

        String opis = posao.getOpis();
        String naslov = posao.getNaslov();
        String lokacija = posao.getLokacija();
        Integer zarada = posao.getPonudeniNovac();
        Long trajanje = posao.getTrajanje();
        String poslodavac = posao.getPoslodavacId();
        String vrijeme = Util.datumIz(posao.getVrijeme().toString());

        List<Kategorija> kategorije = posao.getKategorije();
        Kategorija prikazanaKategorija = kategorije.get(0);
        String nazivKategorije = prikazanaKategorija.getIme();

        nazivKategorijePosla.setText(nazivKategorije);
        opisPosla.setText(opis);
        trajanjePosla.setText(trajanje.toString());
        zaradaPosla.setText(zarada.toString());
        poslodavacPosla.setText(poslodavac);
        lokacijaPosla.setText(lokacija);
        naslovPosla.setText(naslov);
        vrijemePosla.setText(vrijeme);

//        slikaKategorijePosla.setBackgroundResource(Util.slikaZa(kategorije));
        slikaKategorijePosla.setImageResource(Util.slikaZa(kategorije));
    }

    public void izbrisiPosao(View view){
        // Poziv na bazu za brisanjem posla
        Log.d("JobActivity", "izbrisiPosao: " + trenutniPosao.toString());
    }

    public void izmijeniPosao(View view){
        // Poziv na bazu za izmjenom posla
        Log.d("JobActivity", "izmijeniPosao: moram izmjeniti" + trenutniPosao.toString());

//        String nazivKategorije = (String) nazivKategorijePosla.getText();
//        String opis = (String) opisPosla.getText();
//        String trajanje = (String) trajanjePosla.getText();
//        String zarada = (String) zaradaPosla.getText();
//
////        trajanjePosla.setText(trajanje.toString()+ " min");
////        zaradaPosla.setText(zarada.toString()+ " kn");
////        poslodavacPosla.setText(poslodavac);
////        lokacijaPosla.setText(lokacija);
////        naslovPosla.setText(naslov);
//
//        Log.d("JobActivity", "novi posao ce biti" + trenutniPosao.toString());

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
