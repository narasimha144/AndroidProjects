package com.example.filesdownloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class DownloadTask extends AsyncTask<String, Integer, String> {

    private static final String TAG = "downloadAsyncTask";
    private static final String fileName = "AMVY21.pdf";
    private static final String downloadedFilePath = "com.alphonso.tv.filepath";
    private static int i = 0;

    ResultReceiver mResultReceiver;

    DownloadTask(ResultReceiver resultReceiver) {
        mResultReceiver = resultReceiver;
    }

    @Override
    protected String doInBackground(String... strings) {
        i+=1;
        File downloadFile = new File(Environment.getExternalStorageDirectory() + "/"+ fileName);
        if (downloadFile.exists())
            downloadFile.delete();
        try {

            downloadFile.createNewFile();

            URL downloadURL = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) downloadURL.openConnection();

            InputStream is = conn.getInputStream();
            FileOutputStream os = new FileOutputStream(downloadFile);

            byte buffer[] = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                Log.d(TAG, String.format("writing %s", i));
                os.write(buffer, 0, byteCount);
            }
            os.close();
            is.close();

            String filePath = downloadFile.getPath();
            return filePath;
        }
        catch(Exception e) {
            Log.e(TAG,"Error downloading the file", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null) {
            Bundle bundle = new Bundle();
            bundle.putString(downloadedFilePath, result);
            mResultReceiver.send(1, bundle);
        }
        else {
            mResultReceiver.send(-1, null);
        }
    }
}

