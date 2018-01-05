package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import hr.fer.opp.onedayjob.R;

public class MailboxActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView usersListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);

        //Button chatButton = (Button) findViewById(R.id.chat_button);

        //chatButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
            //    openChat();
            //}
        //});

        String[] users= {"Ivan Ivanic", "Luka Lukic", "Marija Marijanovic", "Pero Peric", "Pero Petricic", "Ana Anicic"};

        usersListV=(ListView) findViewById(R.id.listV);

        ArrayAdapter<String> userAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        usersListV.setAdapter(userAdapter);
        usersListV.setOnItemClickListener(this);

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
