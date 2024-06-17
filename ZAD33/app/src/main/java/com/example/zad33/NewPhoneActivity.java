package com.example.zad33;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewPhoneActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_MANUFACTURER = "com.example.android.phonelistsql.REPLY_MANUFACTURER";
    public static final String EXTRA_REPLY_MODEL = "com.example.android.phonelistsql.REPLY_MODEL";
    public static final String EXTRA_REPLY_ANDROID_VERSION = "com.example.android.phonelistsql.REPLY_ANDROID_VERSION";
    public static final String EXTRA_REPLY_WEBPAGE = "com.example.android.phonelistsql.REPLY_WEBPAGE";

    private EditText mEditPhoneText1,mEditPhoneText2,mEditPhoneText3,mEditPhoneText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_phone);
        mEditPhoneText1 = findViewById(R.id.TextInput1);
        mEditPhoneText2 = findViewById(R.id.TextInput2);
        mEditPhoneText3 = findViewById(R.id.TextInput3);
        mEditPhoneText4 = findViewById(R.id.TextInput4);
        final Button buttonDodaj = findViewById(R.id.buttonDodaj);
        buttonDodaj.setOnClickListener(view -> {

            if (TextUtils.isEmpty(mEditPhoneText1.getText().toString())) {
                mEditPhoneText1.setError(getString(R.string.error_word));
                return;
            }
            String manufacturer = mEditPhoneText1.getText().toString();

            if (TextUtils.isEmpty(mEditPhoneText2.getText().toString())) {
                mEditPhoneText2.setError(getString(R.string.error_word));
                return;
            }
            String model = mEditPhoneText2.getText().toString();

            if (TextUtils.isEmpty(mEditPhoneText3.getText().toString())) {
                mEditPhoneText3.setError(getString(R.string.error_word));
                return;
            }
            String androidVersion = mEditPhoneText3.getText().toString();

            if (TextUtils.isEmpty(mEditPhoneText4.getText().toString())) {
                mEditPhoneText4.setError(getString(R.string.error_word));
                return;
            }
            String webpage = mEditPhoneText4.getText().toString();

            Intent replyIntent = new Intent();

            replyIntent.putExtra(EXTRA_REPLY_MANUFACTURER, manufacturer);
            replyIntent.putExtra(EXTRA_REPLY_MODEL, model);
            replyIntent.putExtra(EXTRA_REPLY_ANDROID_VERSION, androidVersion);
            replyIntent.putExtra(EXTRA_REPLY_WEBPAGE, webpage);

            setResult(RESULT_OK, replyIntent);
            finish();
        });
        final Button buttonAnuluj = findViewById(R.id.buttonAnuluj);
        buttonAnuluj.setOnClickListener(view->{
            Toast.makeText(this,"Anulowanie",
                    Toast.LENGTH_SHORT).show();
            finish();
        });
        Toolbar toolbar = findViewById(R.id.toolbarNewPhone);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.side,menu);
        return super.onCreateOptionsMenu(menu);
    }
}