package com.example.beberenbabia.prejuicios;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beberenbabia.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ActivityPrejuicios extends AppCompatActivity {

    private TextView textViewPregunta, textViewVidas;
    private Button buttonSiguiente, buttonFallo, buttonReiniciar;

    private List<String> preguntas;
    private int vidas = 3;
    private int aciertos = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prejuicios);

        textViewPregunta = findViewById(R.id.textViewPregunta);
        textViewVidas = findViewById(R.id.textViewVidas);
        buttonSiguiente = findViewById(R.id.buttonSiguiente);
        buttonFallo = findViewById(R.id.buttonFallo);
        buttonReiniciar = findViewById(R.id.buttonReiniciar);

        cargarPreguntas();

        buttonSiguiente.setOnClickListener(v -> siguientePregunta());
        buttonFallo.setOnClickListener(v -> falloPregunta());
        buttonReiniciar.setOnClickListener(v -> reiniciarJuego());

        siguientePregunta();
    }

    private void cargarPreguntas() {
        preguntas = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.preguntas_prejuicios);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                preguntas.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(preguntas);
    }

    private void siguientePregunta() {
        if (preguntas.isEmpty()) {
            cargarPreguntas();
        }
        textViewPregunta.setText(preguntas.remove(random.nextInt(preguntas.size())));
        aciertos++;
    }

    private void falloPregunta() {
        vidas--;
        if (vidas <= 0) {
            textViewVidas.setText("Vidas: " + vidas);
            mostrarResultado();
        } else {
            textViewVidas.setText("Vidas: " + vidas);
            siguientePregunta();
        }
    }

    private void mostrarResultado() {
        aciertos=aciertos-3;
        textViewPregunta.setText("Has acertado " + aciertos + " preguntas antes de perder todas las vidas.");
        buttonSiguiente.setVisibility(View.GONE);
        buttonFallo.setVisibility(View.GONE);
        buttonReiniciar.setVisibility(View.VISIBLE);
    }

    private void reiniciarJuego() {
        vidas = 3;
        aciertos = 0;
        textViewVidas.setText("Vidas: " + vidas);
        buttonSiguiente.setVisibility(View.VISIBLE);
        buttonFallo.setVisibility(View.VISIBLE);
        buttonReiniciar.setVisibility(View.GONE);
        siguientePregunta();
    }
}