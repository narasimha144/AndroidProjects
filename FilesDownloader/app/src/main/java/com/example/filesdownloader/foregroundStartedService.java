package com.example.filesdownloader;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.concurrent.ExecutionException;

public class foregroundStartedService extends Service {
    private static final String TAG = "foregroundService";
    private static final String downloadingFileUrl = "com.alphonso.tv.file";
    private static final String resultReceiverName = "com.alphonso.tv.receiver";
    private static final String isIntentStart = "com.alphonso.tv.start";

    public foregroundStartedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand called");

        startForeground();

        String fileUrl = intent.getStringExtra(downloadingFileUrl);
        Log.d(TAG, fileUrl);

        final ResultReceiver resultReceiver = intent.getParcelableExtra(resultReceiverName);

        try {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        new DownloadTask(resultReceiver).execute(fileUrl).get();
                        stopForeground(true);
                        stopSelf();
                    }
                    catch (Exception e) {
                        Log.e(TAG, "Error executing download task", e);
                    }
                }
            };
            final Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        runnable.run();
                    }
                    catch(Exception e) {
                        Log.e(TAG, "Error executing new thread", e);
                    }
                }
            };
            t.start();
        }
        catch (Exception e) {
            Log.e(TAG, "Error executing download task", e);
        }

        return START_NOT_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);

        String channelId = "channelId";
        createNotificationChannel(channelId);

        Notification notification = new Notification.Builder(this, channelId)
                .setContentTitle("contentTitle")
                .setContentText("contentText")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId) {

        String channelName = "channelName";
        String description = "channelDescription";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}