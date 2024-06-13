package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText nameInputEditText;
    private TextInputEditText lastnameInputEditText;
    private TextInputEditText gradesInputEditText;
    private static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button finalButton = findViewById(R.id.finalButton);
        finalButton.setVisibility(View.GONE);
        nameInputEditText = findViewById(R.id.nameTextInputEditText);
        lastnameInputEditText = findViewById(R.id.lastnameTextInputEditText);
        gradesInputEditText = findViewById(R.id.gradesTextInputEditText);
        finalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        nameInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (nameInputEditText.getText().toString().isEmpty() && !hasFocus) {
                    Toast.makeText(MainActivity.this, "Puste imie", Toast.LENGTH_SHORT).show();
                }
                checkValues();
            }
        });

        lastnameInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (lastnameInputEditText.getText().toString().isEmpty() && !hasFocus) {
                    Toast.makeText(MainActivity.this, "Puste nazwisko", Toast.LENGTH_SHORT).show();
                }
                checkValues();
            }
        });

        gradesInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    int grade = Integer.parseInt(gradesInputEditText.getText().toString());
                    if ((grade < 5 || grade > 15) && !hasFocus) {
                        Toast.makeText(MainActivity.this, "Złe oceny", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    if (!hasFocus) {
                        Toast.makeText(MainActivity.this, "Nieprawidłowy format ocen", Toast.LENGTH_SHORT).show();
                    }
                }
                checkValues();
            }
        });

        checkValues();
        Button gradesButton = findViewById(R.id.gradesButton);
        gradesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int numberOfGrades = Integer.parseInt(gradesInputEditText.getText().toString());
                Intent intent = new Intent(MainActivity.this,SideActivity.class);
                intent.putExtra("numberOfGrades",numberOfGrades);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private void checkValues() {
        Button gradesButton = findViewById(R.id.gradesButton);
        try {
            int grade = Integer.parseInt(gradesInputEditText.getText().toString());
            if (grade >= 5 && grade <= 15 && !lastnameInputEditText.getText().toString().isEmpty() && !nameInputEditText.getText().toString().isEmpty()) {
                gradesButton.setVisibility(View.VISIBLE);
            } else {
                gradesButton.setVisibility(View.GONE);
            }
        } catch (NumberFormatException e) {
            gradesButton.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Button finalButton = findViewById(R.id.finalButton);
        TextView averageVal = findViewById(R.id.averageVal);
        finalButton.setVisibility(View.VISIBLE);

        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.hasExtra("average")) {
            double average = data.getDoubleExtra("average", 0.0);
            if(average>=3){
                finalButton.setText("Super :)");
            }else{
                finalButton.setText("Tym razem mi nie poszło");
            }
            averageVal.setText(average+"");
        }

    }
}
