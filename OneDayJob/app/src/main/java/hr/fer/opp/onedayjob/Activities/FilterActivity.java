package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.fer.opp.onedayjob.Models.Filter;
import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.R;

public class FilterActivity extends AppCompatActivity {

    Korisnik korisnik;

    ImageView cover;
    Spinner category;
    Button submit;
    Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        loadUserData();
        category = (Spinner) findViewById (R.id.filter_dropDownMenu);
        cover = (ImageView) findViewById(R.id.filter_category_image);
        submit = (Button) findViewById (R.id.filter_submit);
        remove = (Button) findViewById (R.id.filter_remove);

        String[] plants = new String[]{
                "Kategorija",
                Kategorija2.INSTRUKCIJE.getIme(),
                Kategorija2.FIZICKI_POSAO.getIme(),
                Kategorija2.CUVANJE_ZIVOTINJE.getIme(),
                Kategorija2.CUVANJE_DJETETA.getIme(),
                Kategorija2.CISCENJE.getIme(),
                Kategorija2.VOZNJA.getIme()

        };

        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,plantsList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(spinnerArrayAdapter);


        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String text = category.getSelectedItem().toString();

                String monthString;
                switch (text) {
                    case "Kategorija":
                        cover.setImageResource(R.drawable.kategorija);
                        break;
                    case "Instrukcije":
                        cover.setImageResource(R.drawable.instrukcije);
                        break;
                    case "Fizički posao":
                        cover.setImageResource(R.drawable.fizicki_rad);
                        break;
                    case "Čuvanje životinja":
                        cover.setImageResource(R.drawable.cuvanje_zivotinja);
                        break;
                    case "Čuvanje djece":
                        cover.setImageResource(R.drawable.cuvanje_djece);
                        break;
                    case "Čišćenje":
                        cover.setImageResource(R.drawable.ciscenje);
                        break;
                    case "Vožnja":
                        cover.setImageResource(R.drawable.voznja);
                        break;
                    default:
                        cover.setImageResource(R.drawable.orange_background);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setClickable(false);

                String kategorija = category.getSelectedItem().toString();

                //Provjera je li odabrana kategorija
                if (kategorija.equals("Kategorija")){
                    ToastAndClickable("Niste odabrali kategoriju");
                    return;
                }

                Filter filter = new Filter();
                filter.setKategorija(kategorija);
                filter.setKategorijaID(Kategorija2.KategorijaStringToID(kategorija));


                Intent intent = new Intent(FilterActivity.this, TheMainActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("korisnik", korisnik);
                bundle.putSerializable("filter",filter);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, TheMainActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("korisnik", korisnik);
                bundle.putSerializable("filter",null);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });



    }


    private void ToastAndClickable(String txt){
        Toast.makeText(FilterActivity.this, txt,Toast.LENGTH_LONG).show();
        submit.setClickable(true);
    }

    private void loadUserData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        korisnik = (Korisnik) bundle.get("korisnik");
    }

}
