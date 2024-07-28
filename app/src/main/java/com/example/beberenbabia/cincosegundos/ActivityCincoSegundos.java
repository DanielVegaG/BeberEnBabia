package com.example.beberenbabia.cincosegundos;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beberenbabia.R;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityCincoSegundos extends AppCompatActivity {
    private TextView textViewCategoria;
    private TextView textViewTimer;
    private Button buttonStart;
    private Button buttonSiguiente;
    private Button buttonIncrease;
    private Button buttonDecrease;

    private List<String> categorias;
    private CountDownTimer countDownTimer;
    private int tiempoRespuesta = 5000; // tiempo de respuesta en milisegundos (por defecto 5 segundos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_segundos);

        textViewCategoria = findViewById(R.id.textViewCategoria);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonStart = findViewById(R.id.buttonStart);
        buttonSiguiente = findViewById(R.id.buttonSiguiente);
        buttonIncrease = findViewById(R.id.buttonIncrease);
        buttonDecrease = findViewById(R.id.buttonDecrease);

        categorias = obtenerCategorias();

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoRespuesta += 1000;
                actualizarTiempo();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tiempoRespuesta > 1000) {
                    tiempoRespuesta -= 1000;
                    actualizarTiempo();
                }
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStart.setVisibility(View.GONE);
                buttonSiguiente.setVisibility(View.VISIBLE);
                buttonIncrease.setVisibility(View.GONE);
                buttonDecrease.setVisibility(View.GONE);
                mostrarNuevaCategoria();
                iniciarCuentaRegresiva();
            }
        });

        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarNuevaCategoria();
                iniciarCuentaRegresiva();
            }
        });
    }

    private void iniciarCuentaRegresiva() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(tiempoRespuesta, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                textViewTimer.setText("0");
                Toast.makeText(ActivityCincoSegundos.this, "Se acabó el tiempo!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void mostrarNuevaCategoria() {
        String categoria = categorias.get(new Random().nextInt(categorias.size()));
        textViewCategoria.setText(categoria);
    }

    private List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.categorias_cinco_segundos);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                categorias.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return categorias;
    }

    private void actualizarTiempo() {
        textViewTimer.setText(String.valueOf(tiempoRespuesta / 1000));
    }
}