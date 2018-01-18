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
import hr.fer.opp.onedayjob.Servisi.KorisnikServis;
import hr.fer.opp.onedayjob.util.Util;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    Korisnik noviKorisnik;

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
        pass1.setText("007Bond");
        pass2.setText("007Bond");
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
        //TODO : makni da je admin
        noviKorisnik = new Korisnik(1, fName, lName, email, passHash, Short.parseShort(age), description, 1828932987, phoneN, false, true);

        if (fName.isEmpty() || lName.isEmpty() || lName.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty() || description.isEmpty() || phoneN.isEmpty()) {
            Toast.makeText(this, "Sva polja su obavezna!", Toast.LENGTH_SHORT).show();
            firstName.requestFocus();
            return;
        } else if (!LoginActivity.isPasswordValid(password)){
            Toast.makeText(this, "Minimalna duljina zaporke je 4!", Toast.LENGTH_SHORT).show();
            pass1.requestFocus();
            return;
        } else if(!LoginActivity.isEmailValid(email)){
            Toast.makeText(this, "Email nije valjan!", Toast.LENGTH_SHORT).show();
            mail.requestFocus();
            return;
        } else if (!password.equals(password2)) {
            Toast.makeText(this, "Zaporke se ne poklapaju!", Toast.LENGTH_SHORT).show();
            pass1.requestFocus();
            return;
        }else if(!avatarSet){
            Toast.makeText(this, "Molim vas da pritiskom promjenite sliku na vrhu ekrana", Toast.LENGTH_SHORT).show();
            avatar.requestFocus();
            return;
        }

        Log.d("REGISTER", "Ovdje šaljem mail korisniku!");
        // Logging ...
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://onedayjobapp2.azurewebsites.net")
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(httpClient.build())
                .build();
        final KorisnikServis service = retrofit.create(KorisnikServis.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String email = mail.getText().toString();
                service.verifyKorisnik(email).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        String ocekivaniKod = response.body();

                        Log.d("REG", "onResponse: Verificiram korisnika!");
                        Toast.makeText(RegisterActivity.this, "Verifikacijski kod je poslan na " + email, Toast.LENGTH_SHORT).show();

                        // Idem na VerifyActivity
                        Bundle bundla = new Bundle();
                        bundla.putSerializable("noviKorisnik", noviKorisnik);
                        bundla.putString("kod", ocekivaniKod);
                        Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
                        intent.putExtras(bundla);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("VERIF KOD:", "onFailure: " + t.getMessage());
                        Toast.makeText(RegisterActivity.this, "Nisam uspio poslati verifikacijski kod. Razlog: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).run();
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
