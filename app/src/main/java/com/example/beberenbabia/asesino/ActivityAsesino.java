package com.example.beberenbabia.asesino;

import android.os.Bundle;
import com.example.beberenbabia.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class ActivityAsesino extends AppCompatActivity {
    private TextView tvJugador;
    private ImageView ivCarta;
    private TextView tvRol;
    private Button btnVerCarta;

    private ArrayList<String> cartas;
    private int jugadorActual;
    private int numJugadores;
    private int numCartasMostradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesino);

        tvJugador = findViewById(R.id.tvJugador);
        ivCarta = findViewById(R.id.ivCarta);
        tvRol = findViewById(R.id.tvRol);
        btnVerCarta = findViewById(R.id.btnVerCarta);

        numJugadores = getIntent().getIntExtra("NUM_JUGADORES", 4);
        prepararCartas();
        jugadorActual = 1;
        numCartasMostradas = 0;

        btnVerCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnVerCarta.getText().toString().equals("Ver carta")) {
                    mostrarCarta();
                } else if (jugadorActual < numJugadores) {
                    siguienteJugador();
                } else {
                    mostrarInstrucciones();
                }
            }
        });
    }

    private void prepararCartas() {
        cartas = new ArrayList<>();
        cartas.add("as_de_oros");
        cartas.add("rey_de_bastos");
        cartas.add("sota_de_copas");

        for (int i = 0; i < numJugadores - 3; i++) {
            cartas.add("cinco_de_espadas");
        }

        Collections.shuffle(cartas);
    }

    private void mostrarCarta() {
        String carta = cartas.get(numCartasMostradas);
        int resId = getResources().getIdentifier(carta, "drawable", getPackageName());
        ivCarta.setImageResource(resId);
        tvRol.setText(obtenerRol(carta));
        tvRol.setVisibility(View.VISIBLE);
        btnVerCarta.setText("Siguiente jugador");
        numCartasMostradas++;
    }

    private void siguienteJugador() {
        if (jugadorActual < numJugadores) {
            jugadorActual++;
            tvJugador.setText("Jugador " + jugadorActual);
            ivCarta.setImageResource(R.drawable.baraja);
            tvRol.setVisibility(View.GONE);
            btnVerCarta.setText("Ver carta");
        } else {
            mostrarInstrucciones();
        }
    }

    private void mostrarInstrucciones() {
        setContentView(R.layout.activity_asesino_instrucciones);

        TextView tvInstrucciones = findViewById(R.id.tvInstrucciones);
        tvInstrucciones.setText(obtenerInstrucciones());

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarJuego();
            }
        });
    }

    private String obtenerRol(String carta) {
        switch (carta) {
            case "as_de_oros":
                return "Asesino";
            case "rey_de_bastos":
                return "Policía";
            case "sota_de_copas":
                return "Ángel";
            default:
                return "Pueblo";
        }
    }

    private String obtenerInstrucciones() {
        return "Instrucciones de los roles:\n\n" +
                "Asesino: Guiña un ojo para 'matar' a otros jugadores y saca la lengua para hacer aliados. El objetivo es eliminar a todos sin ser descubierto.\n\n" +
                "Policía: Intenta descubrir quién es el asesino. Puedes acusar a otros jugadores de ser el asesino.\n\n" +
                "Ángel: Puedes 'revivir' a los jugadores muertos con un beso. Tu objetivo es mantener a la mayor cantidad de jugadores vivos.\n\n" +
                "Pueblo: No tienes un rol especial, tu objetivo es sobrevivir y ayudar al policía a descubrir al asesino.";
    }

    private void reiniciarJuego() {
        // Reinicia el juego y vuelve a la actividad principal o inicial
        finish();
    }
}
