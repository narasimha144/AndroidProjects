package com.example.filesdownloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;


public class downloadService extends Service {

    private final String TAG = "downloadService";
    private static final String downloadingFileUrl = "com.alphonso.tv.file";
    private static final String resultReceiverName = "com.alphonso.tv.receiver";

    public downloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand function called");

        String fileUrl = intent.getStringExtra(downloadingFileUrl);
        Log.d(TAG, fileUrl);

        final ResultReceiver resultReceiver = intent.getParcelableExtra(resultReceiverName);

        new DownloadTask(resultReceiver).execute(fileUrl);

        return super.onStartCommand(intent, flags, startId);
    }

}