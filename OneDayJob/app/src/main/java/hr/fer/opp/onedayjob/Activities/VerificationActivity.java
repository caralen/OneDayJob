package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.R;

public class VerificationActivity extends AppCompatActivity {

    @BindView(R.id.verification_code)
    EditText verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);

    }
    void attemptVerification(View view){
        /*// get user email from bundle
        String userGivenCode = verificationCode.getText().toString();
        if(!Util.checkVerification("user email froom bundle", userGivenCode)){
            Toast.makeText(this, "Invalid code given!", Toast.LENGTH_SHORT).show();
            return;
        }*/
        Korisnik noviKorisnik = (Korisnik) getIntent().getExtras().get("noviKorisnik");
        noviKorisnik.setJeValidiran(true);
        //Spremi ga u bazu
        Log.d("vrlo bitno", "attemptVerification: " + noviKorisnik);
       Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
       startActivity(intent);
    }
}
