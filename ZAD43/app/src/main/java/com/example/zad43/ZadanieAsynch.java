package com.example.zad43;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZadanieAsynch extends AsyncTask<String, Void, String> {
    private static final String TAG = "ZadanieAsynch";
    private Context context;
    private TextView rozmiarValueTextView;
    private TextView typPlikuValueTextView;

    public ZadanieAsynch(Context context, TextView rozmiarValueTextView, TextView typPlikuValueTextView) {
        this.context = context;
        this.rozmiarValueTextView = rozmiarValueTextView;
        this.typPlikuValueTextView = typPlikuValueTextView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: Task started");
    }

    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0];
        Log.d(TAG, "doInBackground: URL received: " + urlString);
        HttpURLConnection polaczenie = null;
        try {
            URL url = new URL(urlString);
            polaczenie = (HttpURLConnection) url.openConnection();
            polaczenie.setRequestMethod("HEAD");
            polaczenie.connect();
            Log.d(TAG, "doInBackground: Connection established");

            int contentLength = polaczenie.getContentLength();
            String contentType = polaczenie.getContentType();
            Log.d(TAG, "doInBackground: Content Length: " + contentLength + ", Content Type: " + contentType);
            return contentLength + "}{" + contentType;
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: Error fetching URL info", e);
            return "Error";
        } finally {
            if (polaczenie != null) {
                polaczenie.disconnect();
                Log.d(TAG, "doInBackground: Connection disconnected");
            }
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: Result received: " + result);
        int separatorIndex = result.indexOf("}{");

        if (separatorIndex != -1) {
            String partX = result.substring(0, separatorIndex);
            String partY = result.substring(separatorIndex + 2);

            rozmiarValueTextView.setText(partX);
            typPlikuValueTextView.setText(partY);
            Log.d(TAG, "onPostExecute: Result displayed");
        } else {
            rozmiarValueTextView.setText("Error");
            typPlikuValueTextView.setText("Error");
            Log.e(TAG, "onPostExecute: Error in result format");
        }
    }
}
