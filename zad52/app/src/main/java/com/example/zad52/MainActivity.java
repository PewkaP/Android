package com.example.zad52;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.zad52.PaintSurfaceView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PaintSurfaceView mPaintSurfaceView;
    private List<List<PaintSurfaceView.ColoredPath>> paintings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize mPaintSurfaceView
        mPaintSurfaceView = findViewById(R.id.paintSurfaceView);
        paintings = new ArrayList<>();  // Initialize as a mutable list

        Button red = findViewById(R.id.buttonRED);
        Button green = findViewById(R.id.buttonGREEN);
        Button blue = findViewById(R.id.buttonBLUE);
        Button yellow = findViewById(R.id.buttonYELLOW);
        Button x = findViewById(R.id.buttonX);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = v.getContext().getResources().getColor(R.color.red);
                Log.d("MainActivity", "Red color: " + c);
                mPaintSurfaceView.setColor(c);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = v.getContext().getResources().getColor(R.color.green);
                Log.d("MainActivity", "Green color: " + c);
                mPaintSurfaceView.setColor(c);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = v.getContext().getResources().getColor(R.color.blue);
                Log.d("MainActivity", "Blue color: " + c);
                mPaintSurfaceView.setColor(c);
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = v.getContext().getResources().getColor(R.color.yellow);
                Log.d("MainActivity", "Yellow color: " + c);
                mPaintSurfaceView.setColor(c);
            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaintSurfaceView.clearCanvas();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.zapiszObraz) {
            Toast.makeText(this, "Zapisuję obraz", Toast.LENGTH_SHORT).show();
            paintings.add(mPaintSurfaceView.returnDrawing());
            mPaintSurfaceView.clearCanvas();
            return true;
        } else if (id == R.id.zobaczObrazy) {
            Toast.makeText(this, "Wybierz obraz spośród zapisanych: " + paintings.size(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
