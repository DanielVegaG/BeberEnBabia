package com.example.beberenbabia.hombreslobo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.beberenbabia.R;

import java.util.ArrayList;
import java.util.Collections;

public class ActivityHombresLobo extends AppCompatActivity {

    private TextView tvJugador;
    private ImageView ivCarta;
    private TextView tvRol;
    private Button btnVerCarta;

    private ArrayList<String> cartas;
    private int jugadorActual;
    private int numJugadores;
    private int numCartasMostradas;
    private int numLobos;
    private boolean incluirBruja, incluirCazador, incluirCupido, incluirNarrador, incluirNina, incluirVidente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hombres_lobo);

        tvJugador = findViewById(R.id.tvJugador);
        ivCarta = findViewById(R.id.ivCarta);
        tvRol = findViewById(R.id.tvRol);
        btnVerCarta = findViewById(R.id.btnVerCarta);

        numJugadores = getIntent().getIntExtra("NUM_JUGADORES", 4);
        numLobos = getIntent().getIntExtra("NUM_LOBOS", 1);

        incluirBruja = getIntent().getBooleanExtra("INCLUIR_BRUJA", false);
        incluirCazador = getIntent().getBooleanExtra("INCLUIR_CAZADOR", false);
        incluirCupido = getIntent().getBooleanExtra("INCLUIR_CUPIDO", false);
        incluirNarrador = getIntent().getBooleanExtra("INCLUIR_NARRADOR", false);
        incluirNina = getIntent().getBooleanExtra("INCLUIR_NINA", false);
        incluirVidente = getIntent().getBooleanExtra("INCLUIR_VIDENTE", false);

        prepararCartas();
        jugadorActual = 1;
        numCartasMostradas = 0;

        tvJugador.setText("Jugador " + jugadorActual);

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

        for (int i = 0; i < numLobos; i++) {
            cartas.add("hombre_lobo");
        }

        if (incluirBruja) {cartas.add("bruja");}
        if (incluirCazador) {cartas.add("cazador");}
        if (incluirCupido) {cartas.add("cupido");}
        if (incluirNarrador) {cartas.add("narrador");}
        if (incluirNina) {cartas.add("nina");}
        if (incluirVidente) {cartas.add("vidente");}


        while (cartas.size() < numJugadores) {
            cartas.add("aldeano");
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
        setContentView(R.layout.activity_hombres_lobo_instrucciones);

        TextView tvInstrucciones = findViewById(R.id.tvInstrucciones);
        tvInstrucciones.setText(obtenerInstrucciones());

        Button btnTerminar = findViewById(R.id.btnTerminar);
        btnTerminar.setOnClickListener(v -> reiniciarJuego());
    }

    private String obtenerRol(String carta) {
        switch (carta) {
            case "hombre_lobo":
                return "Hombre Lobo";
            case "bruja":
                return "Bruja";
            case "cazador":
                return "Cazador";
            case "cupido":
                return "Cupido";
            case "narrador":
                return "Narrador";
            case "nina":
                return "Niña";
            case "vidente":
                return "Vidente";
            default:
                return "Aldeano";
        }
    }


    private String obtenerInstrucciones() {
        return "Instrucciones de los roles:\n\n" +
                "Narrador: Dirige la partida, modera el diálogo e informa de los sucesos acontecidos. A su discreción quedan la interpretación de las reglas y las dudas.\n\n" +
                "Cupido: En el turno de preparación, elige a dos jugadores (puede elegirse a sí mismo como uno de los dos jugadores) y los enamora. Los jugadores enamorados pasan a ser un bando más del juego y sólo ganarán si sobreviven al final de la partida; si uno de los dos enamorados muere, el otro se suicida ante \"la horrible idea de vivir sin su amor\". En el caso de que los jugadores enamorados sean un hombre lobo y un aldeano, o dos hombres lobo, deberán eliminar al resto de los jugadores de la partida para poder ganar. Si ambos son aldeanos, únicamente deben llegar vivos al final de la partida, nada les obliga a eliminar al resto de jugadores. Los enamorados no pueden perjudicarse en modo alguno: no pueden acusarse entre ellos ni votarse para ser linchados por el día ni devorados por la noche.\n\n" +
                "Hombres lobo: por la noche, designarán una víctima que será devorada. Debe haber unanimidad en la designación de la víctima o, en caso contrario, no habrá víctima esa noche\n\n" +
                "Niña: Durante el turno de los Hombres Lobo, puede abrir los ojos para tratar de descubrir quiénes son. Debe actuar con cautela, ya que si es descubierta por los Hombres Lobo, pasará a ser automáticamente la víctima de esa noche, en lugar de cualquiera que hubieran decidido.\n\n" +
                "Bruja: Posee dos pociones que puede usar por la noche, tras el turno de los hombres lobo: la Poción de la Vida salvará a un personaje del ataque de los Hombres Lobo (también puede reservársela para ella misma) y la Poción de la Muerte matará a otro personaje de su elección. Puede usar una o ninguna de las dos pociones durante el mismo turno si así lo desea, pero una vez ha utilizado una de las pociones, la pierde para el resto de la partida y no podrá volver a servirse de ella.\n\n" +
                "Vidente: Todas las noches elige un jugador para que el Narrador le revele la carta de personaje correspondiente. El Narrador le muestra a la Vidente, en silencio y en secreto, la carta de dicho jugador. Nadie debe deducir qué carta ha sido mostrada por el ruido de la carta al manipularse ni por la posición del Narrador: si es necesario, el Narrador tocará todas las cartas, pero mostrará a la Vidente únicamente aquella que solicitó.\n\n" +
                "Cazador: Cuando muera, puede elegir a otro jugador a quien matar. La decisión es únicamente suya y no se debe tomar por consenso ni consejo.\n\n" +
                "Aldeano: No posee ningún poder especial, aparte de su intuición y su sentido común.";
    }

    private void reiniciarJuego() {
        finish();
    }
}