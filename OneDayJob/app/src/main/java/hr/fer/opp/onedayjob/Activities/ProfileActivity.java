package hr.fer.opp.onedayjob.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import hr.fer.opp.onedayjob.R;

public class ProfileActivity extends AppCompatActivity {


    ImageView userImage;
    Button submit ;


//  korisnikID: VARCHAR(255) - autogenerirani primarni ključ korisnika
//	ime: VARCHAR(255) - ime korisnika
//	prezime: VARCHAR(255)  - prezime korisnika
//	email: VARCHAR(255) - email korisnika
//	zaporkaHASH: VARCHAR(255) - heširana zaporka korisnika
//	dob: SMALLINT(127) - dob korisnika
//	slikaID: VARCHAR(255) - ključ slike spremljen u drugoj tablici
//	opis: VARCHAR(255)  - opis korisnika
//	datumRegistracije: TIMESTAMP - datum kada se korisnik prvi puta registrirao
//	jeValidiran: BOOLEAN - je li korisnik verificirao svoj korisnički račun
//	brojTelefona: VARCHAR(15) - telefonski broj korisnika


    //TODO
    //prilikom svakog pokretanja 1) dohvati podatke, 2) upisi podatke
    //Klik na sliku --> mogucnost promjene slike
    //Potvrdi --> posalji sve promjene u bazu podataka (Ako je password, provjeri trenutnu, te novu i potvrdi)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //GetKorisnik




        submit = (Button)findViewById(R.id.submit);
        userImage = (ImageView)findViewById(R.id.user_profile_photo);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userImage.setImageResource(R.drawable.user1);


            }
        });






    }


}
