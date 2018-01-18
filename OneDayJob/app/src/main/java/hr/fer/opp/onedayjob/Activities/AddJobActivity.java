package hr.fer.opp.onedayjob.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.util.Util;

public class AddJobActivity extends AppCompatActivity {

    ImageView cover;
    Spinner category;
    Button submit;
    EditText jobTitle; //add_job_naslov_posla
    EditText jobDescription; //add_job_opis_posla
    EditText jobDate; //add_job_vrijeme_posla
    EditText jobDuration; //add_job_posao_trajanje
    EditText jobSalary; //add_job_posao_zarada
    EditText jobLocation; //add_job_posao_lokacija


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        //Makni fokus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        cover = (ImageView)findViewById (R.id.add_job_category_image);
        category = (Spinner) findViewById (R.id.add_job_dropDownMenu);
        submit = (Button) findViewById (R.id.add_job_submit);

        jobTitle = (EditText) findViewById (R.id.add_job_naslov_posla);
        jobDescription = (EditText) findViewById (R.id.add_job_opis_posla);
        jobDate = (EditText) findViewById (R.id.add_job_vrijeme_posla);
        jobDuration = (EditText) findViewById (R.id.add_job_posao_trajanje);
        jobSalary = (EditText) findViewById (R.id.add_job_posao_zarada);
        jobLocation = (EditText) findViewById (R.id.add_job_posao_lokacija);


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
                String naslov = jobTitle.getText().toString();
                String kratkiOpis = jobDescription.getText().toString();
                String datum = jobDate.getText().toString();
                String trajanje = jobDuration.getText().toString();
                String placa = jobSalary.getText().toString();
                String lokacija = jobLocation.getText().toString();



                //Provjera je li odabrana kategorija
                if (kategorija.equals("Kategorija")){
                    ToastAndClickable("Odaberite kategoriju");
                    return;
                }

                //Provjera je li odabrana
                if (naslov.isEmpty()){
                    ToastAndClickable("Upišite naslov posla");
                    return;
                }
                if (naslov.length() < 3){
                    ToastAndClickable("Posao se mora sastojati od barem 3 slova");
                    return;
                }

                if (kratkiOpis.isEmpty()){
                    ToastAndClickable("Upišite kratki opis posla");
                    return;
                }

                if (naslov.length() > 60){
                    ToastAndClickable("Ime posla predugo");
                    return;
                }


                if (datum.isEmpty()){
                    ToastAndClickable("Upišite datum");
                    return;
                }
                //TODO : provjeriti je li datum okej

                if (trajanje.isEmpty()){
                    ToastAndClickable("Upišite trajanje");
                    return;
                }

                try{
                    Long.parseLong(trajanje);
                }catch (Exception ex){
                    ToastAndClickable("Trajanje mora biti broj!");
                    return;
                }

                if (placa.isEmpty()){
                    ToastAndClickable("Upišite iznos plaće");
                    return;
                }

                try{
                    Long.parseLong(placa);
                }catch (Exception ex){
                    ToastAndClickable("plaća mora biti broj!");
                    return;
                }




                ToastAndClickable("Sve okej");




                submit.setClickable(true);
            }
        });







    }

    private void ToastAndClickable(String txt){
        Toast.makeText(AddJobActivity.this, txt,Toast.LENGTH_LONG).show();
        submit.setClickable(true);
    }

    public boolean isValidDate(String input) {

        return true;
//        boolean valid = false;
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        try {
//            Date date = new Date();
//            String dateTime = dateFormat.format(date);
//
//            String txt = dateTime + "";
//            Toast.makeText(AddJobActivity.this, txt,Toast.LENGTH_LONG).show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return valid;
    }



}
