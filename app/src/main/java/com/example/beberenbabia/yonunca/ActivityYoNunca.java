package com.example.beberenbabia.yonunca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.beberenbabia.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

import android.os.Bundle;
import android.view.View;

import java.util.Collections;
import java.util.List;

public class ActivityYoNunca extends AppCompatActivity {
    //variables
    private List<String> preguntas;
    private boolean isTarjetasView = true;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private MaterialButtonToggleGroup toggleButtonGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yo_nunca);

        // Inicializa la lista de preguntas
        //preguntas = PreguntasProvider.getPreguntas();
        preguntas = PreguntasProvider.getPreguntas(getApplicationContext());

        // Inicializa y configura el ViewPager
        viewPager = findViewById(R.id.vpYoNunca);
        TarjetaPagerAdapter adapter = new TarjetaPagerAdapter(getSupportFragmentManager(), preguntas);
        viewPager.setAdapter(adapter);

        // Inicializa y configura el RecyclerView
        recyclerView = findViewById(R.id.rvYoNunca);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PreguntaListAdapter recyclerViewAdapter = new PreguntaListAdapter(preguntas);
        recyclerView.setAdapter(recyclerViewAdapter);

        // Encuentra el ToggleButtonGroup
        MaterialButtonToggleGroup toggleButtonGroup = findViewById(R.id.toggleButtonGroup);

        // Agrega un listener para manejar los cambios en los botones
        toggleButtonGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                // Respond to button selection
                if (isChecked) {
                    if (checkedId == R.id.bYoNuncaTarjeta) {
                        // Mostrar el ViewPager y ocultar el RecyclerView
                        viewPager.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Collections.shuffle(preguntas);
                    } else if (checkedId == R.id.bYoNuncaLista) {
                        // Mostrar el RecyclerView y ocultar el ViewPager
                        viewPager.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }
}