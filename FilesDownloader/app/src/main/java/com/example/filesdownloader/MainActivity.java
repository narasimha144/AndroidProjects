package com.example.filesdownloader;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String downloadingFileUrl = "com.alphonso.tv.file";
    private static final String resultReceiverName = "com.alphonso.tv.receiver";
    private static final String downloadedFilePath = "com.alphonso.tv.filepath";
    private static final String TAG = "MainActivity";

    boundDownloadService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button downloadButton = (Button) findViewById(R.id.download_button);
        downloadButton.setOnClickListener(this);

        DownloadResultReceiver downloadResultReceiver = new DownloadResultReceiver(new Handler());

        Intent startServiceIntent = new Intent(this, BoundStartedService.class);
        startServiceIntent.putExtra(resultReceiverName, downloadResultReceiver);
        startService(startServiceIntent);

//        Intent serviceInvokingIntent = new Intent(this, boundDownloadService.class);
//        serviceInvokingIntent.putExtra(downloadingFileUrl, "https://eprint.iacr.org/2021/848.pdf");
//        serviceInvokingIntent.putExtra(resultReceiverName, downloadResultReceiver);
//        bindService(serviceInvokingIntent, boundConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void askForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
            }
        }
    }

    private class DownloadResultReceiver extends ResultReceiver {

        public DownloadResultReceiver(Handler handler) {
            super(handler);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if(resultCode != 1) {
                Log.e(TAG, "result code from receiver: " + String.valueOf(resultCode));
                return;
            }

            String filePath = resultData.getString(downloadedFilePath);
            Log.d(TAG, "Download successful. Find the downloaded file at " + filePath);
            unbindService(boundStartedConnection);
        }
    }

    private ServiceConnection boundConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boundDownloadService.MyBinder binder = (boundDownloadService.MyBinder) iBinder;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private ServiceConnection boundStartedConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "BoundStartedConnection onServiceConnected called");
            BoundStartedService.BoundStartedBinder binder = (BoundStartedService.BoundStartedBinder) iBinder;
            binder.downloadFile();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "BoundStartedConnection OnServiceDisconnected called");
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        askForPermissions();

        DownloadResultReceiver downloadResultReceiver = new DownloadResultReceiver(new Handler());

//        Intent serviceInvokingIntent = new Intent(this, downloadService.class);
//        Intent serviceInvokingIntent = new Intent(this, foregroundStartedService.class);
//        serviceInvokingIntent.putExtra(downloadingFileUrl, "https://eprint.iacr.org/2021/848.pdf");
//        serviceInvokingIntent.putExtra(resultReceiverName, downloadResultReceiver);

//        startService(serviceInvokingIntent);
//        getApplicationContext().startForegroundService(serviceInvokingIntent);

//        mService.downloadFile();

        Intent bindServiceIntent = new Intent(this, BoundStartedService.class);
        bindServiceIntent.putExtra(downloadingFileUrl, "https://eprint.iacr.org/2021/848.pdf");
        bindService(bindServiceIntent, boundStartedConnection, Context.BIND_AUTO_CREATE);
    }

}