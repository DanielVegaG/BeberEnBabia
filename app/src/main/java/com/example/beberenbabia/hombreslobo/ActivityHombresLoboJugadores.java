package com.example.beberenbabia.hombreslobo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.beberenbabia.R;

public class ActivityHombresLoboJugadores extends AppCompatActivity {

    private TextView tvNumJugadores;
    private TextView tvNumLobos;
    private Switch switchBruja, switchCazador, switchCupido, switchNarrador, switchNina, switchVidente;

    private int numJugadores = 4;
    private int numLobos = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hombres_lobo_jugadores);

        tvNumJugadores = findViewById(R.id.tvNumJugadores);
        tvNumLobos = findViewById(R.id.tvNumLobos);

        switchBruja = findViewById(R.id.switchBruja);
        switchCazador = findViewById(R.id.switchCazador);
        switchCupido = findViewById(R.id.switchCupido);
        switchNarrador = findViewById(R.id.switchNarrador);
        switchNina = findViewById(R.id.switchNina);
        switchVidente = findViewById(R.id.switchVidente);

        Button btnDecrement = findViewById(R.id.btnDecrement);
        Button btnIncrement = findViewById(R.id.btnIncrement);
        Button btnDecrementLobos = findViewById(R.id.btnDecrementLobos);
        Button btnIncrementLobos = findViewById(R.id.btnIncrementLobos);
        Button btnEmpezarPartida = findViewById(R.id.btnEmpezarPartida);

        btnDecrement.setOnClickListener(v -> {
            if (numJugadores > 4) {
                numJugadores--;
                tvNumJugadores.setText(String.valueOf(numJugadores));
                btnIncrement.setEnabled(numJugadores < 20);
                btnDecrement.setEnabled(numJugadores > 4);
            }
        });

        btnIncrement.setOnClickListener(v -> {
            if (numJugadores < 20) {
                numJugadores++;
                tvNumJugadores.setText(String.valueOf(numJugadores));
                btnIncrement.setEnabled(numJugadores < 20);
                btnDecrement.setEnabled(numJugadores > 4);
            }
        });

        btnDecrementLobos.setOnClickListener(v -> {
            if (numLobos > 1) {
                numLobos--;
                tvNumLobos.setText(String.valueOf(numLobos));
                btnIncrementLobos.setEnabled(numLobos < numJugadores - 1);
                btnDecrementLobos.setEnabled(numLobos > 1);
            }
        });

        btnIncrementLobos.setOnClickListener(v -> {
            if (numLobos < numJugadores - 1) {
                numLobos++;
                tvNumLobos.setText(String.valueOf(numLobos));
                btnIncrementLobos.setEnabled(numLobos < numJugadores - 1);
                btnDecrementLobos.setEnabled(numLobos > 1);
            }
        });

        btnEmpezarPartida.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHombresLoboJugadores.this, ActivityHombresLobo.class);
            intent.putExtra("NUM_JUGADORES", numJugadores);
            intent.putExtra("NUM_LOBOS", numLobos);

            intent.putExtra("INCLUIR_BRUJA", switchBruja.isChecked());
            intent.putExtra("INCLUIR_CAZADOR", switchCazador.isChecked());
            intent.putExtra("INCLUIR_CUPIDO", switchCupido.isChecked());
            intent.putExtra("INCLUIR_NARRADOR", switchNarrador.isChecked());
            intent.putExtra("INCLUIR_NINA", switchNina.isChecked());
            intent.putExtra("INCLUIR_VIDENTE", switchVidente.isChecked());
            startActivity(intent);
        });
    }
}