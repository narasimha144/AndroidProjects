package com.example.filesdownloader;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.ResultReceiver;

public class boundDownloadService extends Service {
    private static final String downloadingFileUrl = "com.alphonso.tv.file";
    private static final String resultReceiverName = "com.alphonso.tv.receiver";

    public boundDownloadService() {
    }

    private final IBinder binder = new MyBinder();
    String fileUrl;
    ResultReceiver resultReceiver;

    public class MyBinder extends Binder {
        boundDownloadService getService() {
            return boundDownloadService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        fileUrl = intent.getStringExtra(downloadingFileUrl);
        resultReceiver = intent.getParcelableExtra(resultReceiverName);

        return binder;
    }

    public void downloadFile() {
        new DownloadTask(resultReceiver).execute(fileUrl);
    }
}