package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import hr.fer.opp.onedayjob.R;

public class ChatActivity extends AppCompatActivity {
    //Intent myIntent = getIntent();
    //String talkingTo = myIntent.getStringExtra("talkingTo");

    int[] slike={R.drawable.user1, R.drawable.instrukcije};
    String[] messages={"bok", "posao?"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ListView messegesListView=(ListView) findViewById(R.id.listV);
        CustomAdapter customAdapter=new CustomAdapter();
        messegesListView.setAdapter(customAdapter);


    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return messages.length;
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
            textView.setText(messages[i]);


            return view;
        }
    }
}
