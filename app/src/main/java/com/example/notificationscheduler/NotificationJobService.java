package com.example.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationJobService extends JobService {
    NotificationManager mNotifyManager;

    //Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        /**
         * Creates a Notification channel, for OREO and higher.
         */
        //Create the notification channel
        mNotifyManager.createNotificationChannel(null);

//Set up the notification content intent to launch the app when clicked
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT); {

            // Define notification manager object.
            mNotifyManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // Notification channels are only available in OREO and higher.
            // So, add a check on SDK version.
            if (android.os.Build.VERSION.SDK_INT >=
                    android.os.Build.VERSION_CODES.O) {

                // Create the NotificationChannel with all the parameters.
                NotificationChannel notificationChannel = new NotificationChannel
                        (PRIMARY_CHANNEL_ID,
                                "Job Service notification",
                                NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription
                        ("Notifications from Job Service");

                mNotifyManager.createNotificationChannel(notificationChannel);


            }
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}
