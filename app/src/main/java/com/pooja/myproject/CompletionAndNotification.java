package com.pooja.myproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CompletionAndNotification extends AppCompatActivity {
TextView txt;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_and_notification);
        txt=(TextView)findViewById(R.id.textView_information);
        btn=(Button)findViewById(R.id.button_information);
        final String title=getIntent().getStringExtra("Title");
        btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(CompletionAndNotification.this,MainActivity.class);
               startActivity(i);
           }
       });

    }
}
