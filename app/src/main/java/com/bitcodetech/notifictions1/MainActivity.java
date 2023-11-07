package com.bitcodetech.notifictions1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private Button btnCancel;

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannels();

        setContentView(R.layout.activity_main);
        btnNotify = findViewById(R.id.btnNotify);
        btnCancel = findViewById(R.id.btnCancel);

        btnNotify.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Notification.Builder builder =
                                new Notification.Builder(MainActivity.this);

                        builder.setContentTitle("New Jobs Available");
                        builder.setContentText("There are vacancies at BitCode! Call on xxxxxx for details!");
                        builder.setAutoCancel(true);

                        builder.setColor(Color.RED);
                        builder.setSmallIcon(R.mipmap.ic_launcher);

                        builder.setChannelId("job_updates");

                        notificationManager.notify(
                                1,
                                builder.build()
                        );
                    }
                }
        );

        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notificationManager.cancel(1);
                    }
                }
        );
    }

    private void createNotificationChannels() {

        if(notificationManager.getNotificationChannel("job_updates") == null) {
            NotificationChannel notificationChannelJobUpdates =
                    new NotificationChannel(
                            "job_updates",
                            "Job Updates",
                            NotificationManager.IMPORTANCE_HIGH
                    );
            notificationChannelJobUpdates.setDescription("This channel will show updates regarding new jobs.");
            notificationManager.createNotificationChannel(notificationChannelJobUpdates);
        }

        if(notificationManager.getNotificationChannel("marketing") == null) {
            NotificationChannel notificationChannelMarketing =
                    new NotificationChannel(
                            "marketing",
                            "Marketing",
                            NotificationManager.IMPORTANCE_HIGH
                    );
            notificationChannelMarketing.setDescription("This channel will show updates regarding new offers.");
            notificationManager.createNotificationChannel(notificationChannelMarketing);
        }
    }
}