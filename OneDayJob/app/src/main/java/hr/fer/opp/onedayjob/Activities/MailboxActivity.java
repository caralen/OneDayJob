package hr.fer.opp.onedayjob.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hr.fer.opp.onedayjob.R;

public class MailboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);

        Button chatButton = (Button) findViewById(R.id.chat_button);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChat();
            }
        });
    }

    private void openChat(){
        Intent intent = new Intent(MailboxActivity.this, ChatActivity.class);
        startActivity(intent);
    }
}
