package com.example.beberenbabia.preferir;

import android.os.Bundle;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beberenbabia.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityPreferir2 extends AppCompatActivity {

    private Button buttonOption1, buttonOption2;
    private List<String[]> preguntas;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferir2);

        // Inicializar vistas
        buttonOption1 = findViewById(R.id.buttonOption1);
        buttonOption2 = findViewById(R.id.buttonOption2);

        // Cargar preguntas
        preguntas = cargarPreguntas();
        random = new Random();

        // Configurar listeners para los botones
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarNuevaPregunta();
            }
        };
        buttonOption1.setOnClickListener(listener);
        buttonOption2.setOnClickListener(listener);

        // Mostrar la primera pregunta
        mostrarNuevaPregunta();
    }

    private List<String[]> cargarPreguntas() {
        List<String[]> preguntas = new ArrayList<>();
        Resources res = getResources();
        InputStream inputStream = res.openRawResource(R.raw.preguntas_preferir);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] opciones = line.split(" o ");
                for (int i = 0; i < opciones.length; i++) {
                    opciones[i] = opciones[i].trim();
                }
                preguntas.add(opciones);
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

        return preguntas;
    }

    private void mostrarNuevaPregunta() {
        if (!preguntas.isEmpty()) {
            int index = random.nextInt(preguntas.size());
            String[] pregunta = preguntas.get(index);
            buttonOption1.setText(pregunta[0]);
            buttonOption2.setText(pregunta[1]);
        }
    }
}