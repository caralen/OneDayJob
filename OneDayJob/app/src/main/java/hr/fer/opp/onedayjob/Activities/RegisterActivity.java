package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.util.Util;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_name)
    EditText firstName;

    @BindView(R.id.register_last_name)
    EditText lastName;

    @BindView(R.id.register_pass)
    EditText pass1;

    @BindView(R.id.register_pass2)
    EditText pass2;

    @BindView(R.id.register_phone)
    EditText phone;

    @BindView(R.id.register_mail)
    EditText mail;

    @BindView(R.id.register_birthday)
    EditText birth;

    @BindView(R.id.register_summary)
    EditText desc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    public void attemptRegister(View view){
        Bundle bundla = new Bundle();
        try {
            Log.d("tu1", "attemptRegister: " + mail.getText().toString());
            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            String password = pass1.getText().toString();
            String password2 = pass2.getText().toString();
            String email = mail.getText().toString();
            String birthday = birth.getText().toString();
            String description = desc.getText().toString();
            String phoneN = phone.getText().toString();
            Log.d("tu2", "attemptRegister: ");
            if (fName.isEmpty() || lName.isEmpty() || lName.isEmpty() || password.isEmpty() || email.isEmpty() || birthday.isEmpty() || description.isEmpty() || phoneN.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
                return;
            } else if (!password.equals(password2)) {
                Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                return;
            }

            String passHash = Util.calculatePasswordHash(password);
            Log.d("muchos bitnos", "attemptRegister: " + birthday);
            short age = 21;
            Korisnik noviKorisnik = new Korisnik(fName, lName, email, passHash, age, description, new Date(), phoneN);
            bundla.putSerializable("noviKorisnik", noviKorisnik);
            Log.d("dobar kaj", "attemptRegister: " + noviKorisnik);
        }catch(Exception e){
            e.printStackTrace();
        }
        Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
        intent.putExtras(bundla);
        startActivity(intent);
    }


}
