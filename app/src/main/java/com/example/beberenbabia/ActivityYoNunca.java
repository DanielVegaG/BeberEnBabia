package com.example.beberenbabia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.button.MaterialButtonToggleGroup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityYoNunca extends AppCompatActivity {
    //variables
    private List<String> preguntas;
    private boolean isTarjetasView = true;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private Button bCambiarVista;
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

        // Inicializa y configura el botón de alternancia de vista
        /*bCambiarVista = findViewById(R.id.bYoNuncaCambiarVista);
        bCambiarVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia entre las vistas de tarjetas y lista
                isTarjetasView = !isTarjetasView;
                if (isTarjetasView) {
                    // Mostrar el ViewPager y ocultar el RecyclerView
                    viewPager.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    Collections.shuffle(preguntas);
                } else {
                    // Mostrar el RecyclerView y ocultar el ViewPager
                    viewPager.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });*/
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