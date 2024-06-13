package com.example.zad43;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button pobierzInformacje;
    private Button pobierzPlik;
    private EditText url;
    private TextView rozmiarValueTextView;
    private TextView typPlikuValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pobierzInformacje = findViewById(R.id.buttonPobierzInfo);
        pobierzPlik = findViewById(R.id.buttonPobierzPlik);
        url = findViewById(R.id.urlTextInputEditText);
        rozmiarValueTextView = findViewById(R.id.rozmiarValueTextView);
        typPlikuValueTextView = findViewById(R.id.typPlikuValueTextView);

        Log.d(TAG, "onCreate: Application started");

        pobierzInformacje.setOnClickListener(v -> {
            String urlString = url.getText().toString();
            Log.d(TAG, "onClick: Fetch info button clicked with URL: " + urlString);
            new ZadanieAsynch(this, rozmiarValueTextView, typPlikuValueTextView).execute(urlString);
        });
    }
}
