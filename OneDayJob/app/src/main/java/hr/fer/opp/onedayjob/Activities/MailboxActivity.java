package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import hr.fer.opp.onedayjob.R;

public class MailboxActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView usersListV;

    int[] slike={R.drawable.user1, R.drawable.instrukcije, R.drawable.user1,R.drawable.user1,R.drawable.user1, R.drawable.user1, R.drawable.user1, R.drawable.user1};
    String[] messages={"bok", "posao?", "DAAAA >3Sta ima lima?"};
    String[] users= {"Ivan Ivanic", "Luka Lukic", "Marija Marijanovic", "Pero Peric", "Pernica Petricic", "Ana Anicic", "lalal", "TOni"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);

        new JsonTask().execute("https://onedayjobapp2.azurewebsites.net/poruke?korisnikID1=2&&korisnikID2=3");
        //Button chatButton = (Button) findViewById(R.id.chat_button);

        //chatButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
            //    openChat();
            //}
        //});



        usersListV=(ListView) findViewById(R.id.listV);

        MailboxActivity.CustomAdapter customAdapter=new MailboxActivity.CustomAdapter();
        usersListV.setAdapter(customAdapter);


        //ArrayAdapter<String> userAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        //usersListV.setAdapter(userAdapter);
        usersListV.setOnItemClickListener(this);

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return users.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView3);
            TextView textView= (TextView) view.findViewById(R.id.textView_message);

            //userova slika ovisno cija je poruka
            imageView.setImageResource(slike[i]);
            textView.setText(users[i]);


            return view;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        String talkingTo=(String)usersListV.getItemAtPosition(position);
        //Toast.makeText(this, talkingTo, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MailboxActivity.this, ChatActivity.class);
        intent.putExtra("takingTo", talkingTo);
        startActivity(intent);
    }

}
