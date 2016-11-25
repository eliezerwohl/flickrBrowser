package com.eliezerwohl.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

enum DownloadStatus {IDLE, PROCESSING, NOT_INTIALISED, FAILED_OR_EMPTY, OK}
/**
 * Created by Elie on 11/25/2016.
 */

class GetRawData extends AsyncTask<String, Void, String>{
    private static final String TAG = "GetRAwData";
    private DownloadStatus mDownloadStatus;
    private final MainActivity mCallback;
    public GetRawData(MainActivity callback) {
       this.mDownloadStatus = DownloadStatus.IDLE;
        mCallback = callback;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: " + s);
//        super.onPostExecute(s);
        if (mCallback!=null){
            mCallback.onDownloadComplete(s, mDownloadStatus);
        }
        Log.d(TAG, "onPostExecute: ends");
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        if (strings == null){
            mDownloadStatus = DownloadStatus.NOT_INTIALISED;
            return null;
        }

        try{
            mDownloadStatus = DownloadStatus.PROCESSING;
            URL url = new URL(strings[0]);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int response = connection.getResponseCode();
            Log.d(TAG, "do in background: there response was "  + response);

            StringBuilder result = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//            String line;
//            while (null != (line = reader.readLine())){
            for (String line = reader.readLine(); line!=null; line= reader.readLine()){
                result.append(line).append("\n");
            }
            mDownloadStatus = DownloadStatus.OK;
            return result.toString();


        }catch(MalformedURLException e){
                Log.e(TAG, "Do" + e.getMessage());
            }catch (IOException e){
            Log.e(TAG, "do int backgroudn: IO exception readign data " + e.getMessage());
        }catch (SecurityException e){
            Log.e(TAG, "do in background:secrutiy need permission?" + e.getMessage());
        }
        finally{
            if (connection!=null) {
                connection.disconnect();
            }
            if (reader !=null) {
                try{
                    reader.close();
                }catch (IOException e){
                    Log.e(TAG, "doInBackground: error closing stream " + e.getMessage());
                }
            }
        }
        mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
        return null;
    }
}
