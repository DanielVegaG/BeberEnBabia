package com.example.beberenbabia.asesino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beberenbabia.R;

public class ActivityAsesinoJugadores extends AppCompatActivity {

    private TextView tvNumJugadores;
    private Button btnDecrement;
    private Button btnIncrement;
    private Button btnEmpezarPartida;

    private int numJugadores = 4;
    private static final int MIN_JUGADORES = 4;
    private static final int MAX_JUGADORES = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesino_jugadores);

        tvNumJugadores = findViewById(R.id.tvNumJugadores);
        btnDecrement = findViewById(R.id.btnDecrement);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnEmpezarPartida = findViewById(R.id.btnEmpezarPartida);

        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numJugadores > MIN_JUGADORES) {
                    numJugadores--;
                    updateUI();
                }
            }
        });

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numJugadores < MAX_JUGADORES) {
                    numJugadores++;
                    updateUI();
                }
            }
        });

        btnEmpezarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAsesinoJugadores.this, ActivityAsesino.class);
                intent.putExtra("NUM_JUGADORES", numJugadores);
                startActivity(intent);
            }
        });

        updateUI();
    }

    private void updateUI() {
        tvNumJugadores.setText(String.valueOf(numJugadores));
        btnDecrement.setEnabled(numJugadores > MIN_JUGADORES);
        btnIncrement.setEnabled(numJugadores < MAX_JUGADORES);
    }
}