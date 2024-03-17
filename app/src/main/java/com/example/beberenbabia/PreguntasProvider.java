package com.example.beberenbabia;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreguntasProvider {
    private static List<String> preguntas;

    public static List<String> getPreguntas(Context context) {
        if (preguntas == null) {
            preguntas = new ArrayList<>();
            try {
                // Obtén el InputStream del archivo de texto desde el directorio res/raw
                InputStream inputStream = context.getResources().openRawResource(R.raw.preguntas);

                // Lee el archivo línea por línea y agrega cada línea a la lista de preguntas
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    preguntas.add(line);
                }

                // Cierra el BufferedReader
                reader.close();

                // Mezcla el orden de las preguntas aleatoriamente
                Collections.shuffle(preguntas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return preguntas;
    }
}
