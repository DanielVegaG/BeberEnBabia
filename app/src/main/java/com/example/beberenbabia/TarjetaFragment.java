package com.example.beberenbabia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TarjetaFragment extends Fragment {
    private String pregunta;

    public static TarjetaFragment newInstance(String pregunta) {
        TarjetaFragment fragment = new TarjetaFragment();
        Bundle args = new Bundle();
        args.putString("pregunta", pregunta);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pregunta = getArguments().getString("pregunta");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarjeta, container, false);
        // Configura la vista para mostrar la pregunta en la tarjeta
        // Por ejemplo, actualiza un TextView con la pregunta
        TextView preguntaTextView = view.findViewById(R.id.preguntaTextView);
        preguntaTextView.setText(pregunta);
        return view;
    }
}
