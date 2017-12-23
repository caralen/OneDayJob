package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.util.Util;

public class VerificationActivity extends AppCompatActivity {

    private boolean fillTestCode = true;

    @BindView(R.id.verification_code)
    EditText verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);

        if(fillTestCode){
            verificationCode.setText("3418");
        }
    }
    public void attemptVerification(View view){

        Korisnik noviKorisnik = (Korisnik) getIntent().getExtras().get("noviKorisnik");
        Integer userGivenCode = Integer.parseInt(verificationCode.getText().toString());
        int expected = Util.calculateVerificationHash(noviKorisnik.getEmail());

        if(!userGivenCode.equals(expected)){
            Toast.makeText(this, "Kod nije odgovarajuć! Očekivani kod je " + expected, Toast.LENGTH_SHORT).show();
            return;
        }
        noviKorisnik.setJeValidiran(true);
        Log.d("VERIFICATION", "Upravo sam verificirao: " + noviKorisnik.getEmail());
        //Spremi ga u bazu
       Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
       startActivity(intent);
    }
}
