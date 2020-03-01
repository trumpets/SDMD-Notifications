package gr.academic.city.sdmd.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PROGRESS_NOTIFICATION_ID = 187;

    private int notificationCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btn_create_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }

    private void sendNotification() {
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // This is bad (just for example purposes), get your string from resources
        String text = "You have " + ++notificationCount + " annoying notifications.";
        String notificationTitle = "Annoy-O-Tron";

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_notify_error)  // the status icon
                .setTicker(text)                                     // the status text
                .setWhen(System.currentTimeMillis())                 // the time stamp
                .setContentTitle(notificationTitle)                  // the label of the entry
                .setContentText(text)                                // the contents of the entry
                .setContentIntent(contentIntent)                     // The intent to send when clicked
                .setAutoCancel(true)
                .build();

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // PROGRESS_NOTIFICATION_ID allows you to update the notification later on.
        mNotificationManager.notify(PROGRESS_NOTIFICATION_ID, notification);
    }
}
