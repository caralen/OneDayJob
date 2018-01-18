package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import hr.fer.opp.onedayjob.Models.Kategorija2;
import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Poruka;
import hr.fer.opp.onedayjob.Models.Posao;
import hr.fer.opp.onedayjob.R;
import hr.fer.opp.onedayjob.Servisi.KorisnikServis;
import hr.fer.opp.onedayjob.Servisi.PorukaServis;
import hr.fer.opp.onedayjob.Servisi.PosaoServis;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ChatActivity extends AppCompatActivity {
    //Intent myIntent = getIntent();
    //String talkingTo = myIntent.getStringExtra("talkingTo");
    public static EditText editKucica;

    int[] slike={R.drawable.user1, R.drawable.instrukcije};
    String[] messages={"bok", "posao?"};
    String[] user={" You ", " Franko Krsto Frankopan? "};
    Long korisnik2Id;
    Korisnik korisnik1;
    Korisnik korisnik2;

    List<Poruka> poruke;
    private CustomAdapter customAdapter;

    Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editKucica=(EditText) findViewById(R.id.ed);
        sendButton = (Button) findViewById(R.id.buttonSend);

        poruke = (List<Poruka>)getIntent().getSerializableExtra("poruke");
        Toast.makeText(this, poruke.toString(), Toast.LENGTH_SHORT).show();


        ListView messegesListView=(ListView) findViewById(R.id.listV);
        customAdapter=new CustomAdapter();
        messegesListView.setAdapter(customAdapter);
    }

    public void send(View view) {
        sendButton.setClickable(false);
        if(editKucica.getText().toString().equals("") ){
            Toast.makeText(this, "poruka je prazna", Toast.LENGTH_SHORT).show();
            return;
        }



        //SLANJE PORUKE
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://onedayjobapp2.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        final PorukaServis service = retrofit.create(PorukaServis.class);
        Date date= Calendar.getInstance().getTime();


        final Poruka poruka=new Poruka((long) 0, editKucica.getText().toString(), date.getTime() , TheMainActivity.korisnik.getKorisnikID(), getIntent().getExtras().getLong("poslodavac"));

        service.posaljiPoruku(poruka).enqueue(new Callback<Boolean>() {


            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean povratnaPoruka = response.body();
                if(povratnaPoruka==null){
                    Log.d("LOGIN RETROFIT", "onResponse: nema");
                }else{
                    Log.d("LOGIN RETROFIT", "onResponse: " + povratnaPoruka.toString());
                }
                poruke.add(poruka);
                customAdapter.notifyDataSetChanged();
                editKucica.setText("");
                Toast.makeText(ChatActivity.this, "Poruka poslana", Toast.LENGTH_SHORT).show();
                sendButton.setClickable(true);
            }


            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("LOGIN RETROFIT", "onFailure: nisam se uspio spojit na bazu!");
                Log.d("LOGIN", "onFailure: " + t.getMessage());
                sendButton.setClickable(true);
            }
        });





    }



    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return poruke.size();
        }

        @Override
        public Object getItem(int i) {
            return poruke.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.customlayout2,null);
            //ImageView imageView=(ImageView)view.findViewById(R.id.imageView3);
            TextView textUser= (TextView) view.findViewById(R.id.textView_username);
            TextView textView= (TextView) view.findViewById(R.id.textView_message);

            //userova slika ovisno cija je poruka
            //imageView.setImageResource(slike[i]);

            textView.setText(poruke.get(i).getSadrzaj());
            textView.setBackgroundResource(R.drawable.bg);
            textUser.setText(String.valueOf(poruke.get(i).getPosiljateljId()));

            return view;
        }
    }
}
