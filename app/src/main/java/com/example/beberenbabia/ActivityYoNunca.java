package com.example.beberenbabia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ActivityYoNunca extends AppCompatActivity {
    //variables
    private List<String> preguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yo_nunca);

        // Inicializa la lista de preguntas con tus preguntas deseadas
        preguntas = new ArrayList<>();
        preguntas.add("¿Has viajado al extranjero?");
        preguntas.add("¿Alguna vez has probado sushi?");
        preguntas.add("¿Te gusta la playa?");

        /*pasar directamente la lista de preguntas al adaptador TarjetaPagerAdapter
         en el onCreate de la actividad, donde se configura el adaptador
         */
        TarjetaPagerAdapter adapter = new TarjetaPagerAdapter(getSupportFragmentManager(), preguntas);
        ViewPager viewPager = findViewById(R.id.vpYoNunca);
        viewPager.setAdapter(adapter);

    }
}