package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SideActivity extends AppCompatActivity {
    private List<ModelOceny> listaOcen;
    private RecyclerView recyclerView;
    private AdapterOcen adapter;
    private Double average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] nazwyPrzedmiotow = getResources().getStringArray(R.array.nazwy_przedmiotow);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        int grades = getIntent().getIntExtra("numberOfGrades",5);
        recyclerView = findViewById(R.id.recycleView);
        Button calculateAverageButton = findViewById(R.id.calculateAverageButton);

        // Inicjalizacja listy ocen
        listaOcen = new ArrayList<>();
        for(int i = 0; i < grades; i++){
            listaOcen.add(new ModelOceny(nazwyPrzedmiotow[i]));
        }
        // Ustawienie adaptera dla RecyclerView
        adapter = new AdapterOcen(this, listaOcen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        calculateAverageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAverage();
            }
        });
    }
    private void calculateAverage() {
        Pair<Boolean, Double> pair = adapter.getAverage();
        if(pair.first==true){
            average = pair.second;
            Intent intent = new Intent();
            intent.putExtra("average", average);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(SideActivity.this, "Nie wypełniono wszystkich pól", Toast.LENGTH_SHORT).show();

        }

    }
}