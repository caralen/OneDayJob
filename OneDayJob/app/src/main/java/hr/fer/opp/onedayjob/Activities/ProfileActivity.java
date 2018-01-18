package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.sql.Timestamp;

import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.Servisi.KorisnikServis;
import hr.fer.opp.onedayjob.Servisi.PosaoServis;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 69;

    ImageView userImage;
    Button submit ;
    Korisnik korisnik;

    TextView headerFirstAndLastName;
    TextView headerBio;

    EditText name;
    EditText lastName;
    EditText email;
    EditText bio;
    EditText currentPassword;
    EditText newPasswordOne;
    EditText newPasswordTwo;


    //TODO
    //prilikom svakog pokretanja 1) dohvati podatke, 2) upisi podatke
    //Klik na sliku --> mogucnost promjene slike
    //Potvrdi --> posalji sve promjene u bazu podataka (Ako je password, provjeri trenutnu, te novu i potvrdi)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //GetKorisnik
        loadUserData();

        submit = (Button)findViewById(R.id.submit);
        userImage = (ImageView)findViewById(R.id.user_profile_photo);
        headerFirstAndLastName=(TextView)findViewById(R.id.user_profile_name);
        headerBio=(TextView)findViewById(R.id.user_profile_short_bio);
        name=(EditText)findViewById(R.id.edit_user_name);
        lastName=(EditText)findViewById(R.id.edit_user_lastname);
        email=(EditText)findViewById(R.id.edit_user_email);
        bio=(EditText)findViewById(R.id.edit_user_bio);
        currentPassword=(EditText)findViewById(R.id.edit_user_currentPassword);
        newPasswordOne=(EditText)findViewById(R.id.edit_user_newPasswordOne);
        newPasswordTwo=(EditText)findViewById(R.id.edit_user_newPasswordTwo);


        headerFirstAndLastName.setText(korisnik.getIme()+ " " + korisnik.getPrezime());
        headerBio.setText(korisnik.getOpis());
        name.setText(korisnik.getIme());
        lastName.setText(korisnik.getPrezime());
        email.setText(korisnik.getEmail());
        bio.setText(korisnik.getOpis());





        Log.d("trump", "onCreate: Trebao bih staviti trumpa");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dohvati ovdje sve s ekrana korisnika i pretvori u objekt Korisnik
                Log.d("ProfileActivity", "onClick: zelim spremiti sve promjene u bazu za korisnika:");
                boolean sucess = false;
                submit.setClickable(false);

                String newName=null;
                String newLastName=null;
                String newEmail=null;
                String newBio=null;
                String newOne=null;
                String newTwo=null;

                String zaporkaHash=korisnik.getZaporkaHash().toString();

                newName=name.getText().toString();
                newLastName=lastName.getText().toString();
                newEmail=email.getText().toString();
                newBio=bio.getText().toString();
                newOne=newPasswordOne.getText().toString();
                newTwo=newPasswordTwo.getText().toString();


                if (newName.isEmpty()){
                    ToastAndClickable("Ime ne smije biti prazno!");
                    return;
                }

                if (newName.length() > 30){
                    ToastAndClickable("Ime smije sadržavati najviše 30 znakova");
                    return;
                }

                if (newLastName.isEmpty()){
                    ToastAndClickable("Prezime ne smije biti prazno!");
                    return;
                }

                if (newLastName.length() > 30){
                    ToastAndClickable("Prezime smije sadržavati najviše 30 znakova");
                    return;
                }

                if (newEmail.isEmpty()){
                    ToastAndClickable("Polje E-Mail ne smije biti prazno!");
                    return;
                }

                if (newEmail.length() > 40){
                    ToastAndClickable("E-Mail smije sadržavati najviše 40 znakova");
                    return;
                }

                if (newBio.isEmpty()){
                    ToastAndClickable("Polje O meni ne smije biti prazno!");
                    return;
                }




                if(!currentPassword.getText().toString().equals(korisnik.getZaporkaHash())){
                    ToastAndClickable("Netočna zaporka! Za svaku promjenu je potrebna zaporka");
                    return;
                }

                if (newOne.isEmpty()&&newTwo.isEmpty()){
                    sucess=true;
                }else{
                    if (!newOne.equals(newTwo)){
                        ToastAndClickable("Nova zaporka se ne poklapa s potvrdnom.");
                        return;
                    }
                    if (newOne.isEmpty()){
                        ToastAndClickable("Zaporka mora imati minimalno 4 znaka");
                        return;
                    }

                    if (newOne.length() < 4){
                        ToastAndClickable("Zaporka mora imati minimalno 4 znaka");
                        return;
                    }
                    zaporkaHash=newOne;
                }




                if(sucess){
                    //novi intent odi

                    final Korisnik updateani = new Korisnik(korisnik.getKorisnikID(),newName,newLastName,newEmail,zaporkaHash,korisnik.getDob(),newBio,korisnik.getDatumRegistracije(),korisnik.getBrojTelefona(),korisnik.isJeValidiran(),korisnik.isJeAdmin());


                    //POSALJI U BAZU +

                    // Logging ...
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.addInterceptor(logging);  // <-- this is the important line!

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://onedayjobapp2.azurewebsites.net")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .client(httpClient.build())
                            .build();
                    final KorisnikServis service = retrofit.create(KorisnikServis.class);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            service.updateKorisnik(updateani).enqueue(new Callback<Korisnik>() {
                                @Override
                                public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                                    Log.d("Login", "onResponse: " + response.body());
                                    submit.setClickable(true);

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("korisnik", updateani); //OVDJE TREBA NOVI KORISNIK
                                    Intent intent = new Intent(ProfileActivity.this, TheMainActivity.class);
                                    intent.putExtras(bundle);
                                    Toast.makeText(ProfileActivity.this, "Promjena prihvaćena",Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<Korisnik> call, Throwable t) {
                                    Log.d("Login", "onFailure: " + t.getMessage());
                                    Toast.makeText(ProfileActivity.this, "Nisam uspio spremiti podatke", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).run();

                    //POSALJI U BAZU -
                }else{
                    submit.setClickable(true);
                }

            }
        });
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
//                avatarSet = true;
                Picasso.with(ProfileActivity.this).load(data.getData()).noPlaceholder().centerCrop().fit().into(userImage);
            }
        }
    }

    private void loadUserData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        korisnik = (Korisnik) bundle.get("korisnik");
    }

    private void ToastAndClickable(String txt){
        Toast.makeText(ProfileActivity.this, txt,Toast.LENGTH_LONG).show();
        submit.setClickable(true);
    }

}
