package com.example.beberenbabia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beberenbabia.preferir.ActivityPreferir2;
import com.example.beberenbabia.yonunca.ActivityYoNunca;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bYoNunca = findViewById(R.id.bYoNunca);
        bYoNunca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la nueva actividad
                Intent intent = new Intent(MainActivity.this, ActivityYoNunca.class);
                startActivity(intent);
            }
        });

        Button bPrefieres = findViewById(R.id.bPrefieres);
        bPrefieres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir la nueva actividad
                Intent intent = new Intent(MainActivity.this, ActivityPreferir2.class);
                startActivity(intent);
            }
        });
    }
}