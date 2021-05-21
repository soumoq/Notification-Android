package com.arobit.notificationpoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button channel1;
    private Button channel2;

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channel1 = findViewById(R.id.channel_1);
        channel2 = findViewById(R.id.channel_2);

        notificationManager = NotificationManagerCompat.from(this);


        channel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(v.getContext(),App.CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_baseline_exposure_plus_1_24)
                        .setContentTitle("Channel_1")
                        .setContentText("This is channel 1")
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManager.notify(1,notification);
            }
        });

        channel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(v.getContext(),App.CHANNEL_2_ID)
                        .setSmallIcon(R.drawable.ic_baseline_exposure_plus_1_24)
                        .setContentTitle("Channel_2")
                        .setContentText("This is channel 2")
                        .setPriority(Notification.PRIORITY_LOW)
                        .build();
                notificationManager.notify(2,notification);
            }
        });


    }
}