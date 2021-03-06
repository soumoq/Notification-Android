package com.arobit.notificationpoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button channel1;
    private Button channel2;

    private NotificationManagerCompat notificationManager;
    private MediaSessionCompat mediaSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channel1 = findViewById(R.id.channel_1);
        channel2 = findViewById(R.id.channel_2);

        notificationManager = NotificationManagerCompat.from(this);
        mediaSession = new MediaSessionCompat(this,"MediaSessionCompat");


        channel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent activityIntent = new Intent(v.getContext(),MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivities(v.getContext(),0, new Intent[]{activityIntent},0);

                Intent broadcastIntent = new Intent(v.getContext(),NotificationReceiver.class);
                broadcastIntent.putExtra("toast","Hi I'm channel one");
                PendingIntent actionIntent = PendingIntent.getBroadcast(v.getContext(),0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.person_dp);

                Notification notification = new NotificationCompat.Builder(v.getContext(),App.CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_baseline_exposure_plus_1_24)
                        .setContentTitle("Channel_1")
                        .setContentText("This is channel 1")
                        .setLargeIcon(largeIcon)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.long_dummy_text))
                        .setBigContentTitle("Bit content title")
                        .setSummaryText("Summery text"))
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setColor(Color.BLUE)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .setOnlyAlertOnce(true)
                        .addAction(R.mipmap.ic_launcher,"Toast",actionIntent)
                        .build();
                notificationManager.notify(1,notification);
            }
        });

        channel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap artWork = BitmapFactory.decodeResource(getResources(),R.drawable.person_dp);


                Notification notification = new NotificationCompat.Builder(v.getContext(),App.CHANNEL_2_ID)
                        .setSmallIcon(R.drawable.ic_baseline_exposure_plus_1_24)
                        .setContentTitle("Channel_2")
                        .setContentText("This is channel 2")
                        .setLargeIcon(artWork)
                        .addAction(R.drawable.ic_baseline_skip_previous_24,"Previous",null)
                        .addAction(R.drawable.ic_baseline_pause_24,"Pause",null)
                        .addAction(R.drawable.ic_baseline_skip_next_24,"Next",null)
                        .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0,1,2)
                        .setMediaSession(mediaSession.getSessionToken()))
                        .setSubText("Sub Text")
                        .setPriority(Notification.PRIORITY_LOW)
                        .build();
                notificationManager.notify(2,notification);
            }
        });


    }
}