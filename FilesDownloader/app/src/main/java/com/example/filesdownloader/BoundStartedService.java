package com.example.filesdownloader;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

public class BoundStartedService extends Service {
    public static final String TAG = "BoundStartedService";
    private static final String downloadingFileUrl = "com.alphonso.tv.file";
    private static final String resultReceiverName = "com.alphonso.tv.receiver";

    public BoundStartedService() {
    }

    private final IBinder binder = new BoundStartedBinder();

    String fileUrl;
    ResultReceiver resultReceiver;

    class BoundStartedBinder extends Binder {
        void downloadFile() {
            new DownloadTask(resultReceiver).execute(fileUrl);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind called");

        fileUrl = intent.getStringExtra(downloadingFileUrl);
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand called");

        resultReceiver = intent.getParcelableExtra(resultReceiverName);
        return super.onStartCommand(intent, flags, startId);
    }

}