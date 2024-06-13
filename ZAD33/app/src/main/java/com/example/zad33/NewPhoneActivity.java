package com.example.zad33;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewPhoneActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.example.android.phonelistsql.REPLY";
    private EditText mEditPhoneText1,mEditPhoneText2,mEditPhoneText3,mEditPhoneText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_phone);
        mEditPhoneText1 = findViewById(R.id.TextInput1);
        mEditPhoneText2 = findViewById(R.id.TextInput2);
        mEditPhoneText3 = findViewById(R.id.TextInput3);
        mEditPhoneText4 = findViewById(R.id.TextInput4);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(mEditPhoneText1.getText().toString())) {
                mEditPhoneText1.setError(getString(R.string.error_word));
                return;
            }
            Intent replyIntent = new Intent();
            String word = mEditPhoneText1.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, replyIntent);
            finish();
        });}}