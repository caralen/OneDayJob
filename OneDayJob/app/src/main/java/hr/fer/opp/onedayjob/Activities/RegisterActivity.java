package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.util.Util;

public class RegisterActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 69;
    private boolean avatarSet = false;

    @BindView(R.id.register_avatar)
    ImageView avatar;

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

    @BindView(R.id.register_years)
    EditText years;

    @BindView(R.id.register_summary)
    EditText desc;

    private boolean enterTestData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        if(enterTestData){
            fillTestData();
        }
    }

    private void fillTestData() {
        firstName.setText("James");
        lastName.setText("Bond");
        pass1.setText("007");
        pass2.setText("007");
        mail.setText("007@MI6.private");
        years.setText("32");
        desc.setText("Agilan, šarmantan i ubojit");
        phone.setText("0500 400 300");
    }

    public void attemptRegister(View view) {

        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String password = pass1.getText().toString();
        String password2 = pass2.getText().toString();
        String email = mail.getText().toString();
        String age = years.getText().toString();
        String description = desc.getText().toString();
        String phoneN = phone.getText().toString();

        String passHash = Util.calculatePasswordHash(password);
        Korisnik noviKorisnik = new Korisnik(fName, lName, email, passHash, Short.parseShort(age), description, new Date(), phoneN);

        if (fName.isEmpty() || lName.isEmpty() || lName.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty() || description.isEmpty() || phoneN.isEmpty()) {
            Toast.makeText(this, "Sva polja su obavezna!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(password2)) {
            Toast.makeText(this, "Zaporke se ne poklapaju!", Toast.LENGTH_SHORT).show();
            return;
        }else if(!avatarSet){
            Toast.makeText(this, "Molim vas da pritiskom promjenite sliku na vrhu ekrana", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundla = new Bundle();
        bundla.putSerializable("noviKorisnik", noviKorisnik);

        Log.d("REGISTER", "Ovdje bih trebao reci bazi da pošalje mail korisniku!");
        Log.d("REGISTER", "ŠALJEM: " + noviKorisnik);

        Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
        intent.putExtras(bundla);
        startActivity(intent);
    }

    public void loadImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                //Get ImageURi and load with help of picasso
                //Uri selectedImageURI = data.getData();
                avatarSet = true;
                Picasso.with(RegisterActivity.this).load(data.getData()).noPlaceholder().centerCrop().fit().into(avatar);
            }
        }
    }
}
