package com.myapplicationdev.android.p06_taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ReplyActivity extends AppCompatActivity {

    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        CharSequence reply = null;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null){
            reply = remoteInput.getCharSequence("status");
        }

        if(reply != null && id != -1){
            if(reply.equals("Completed")){
                DBHelper db = new DBHelper(ReplyActivity.this);
                db.deleteTask(id);
                Toast.makeText(ReplyActivity.this, "You have indicated: " + reply + "\nTask Removed", Toast.LENGTH_SHORT).show();
                finish();
            }else if(reply.equals("Not Yet")){
                Toast.makeText(ReplyActivity.this, "You have indicated: " + reply + "\nTask Untouched", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }
}
