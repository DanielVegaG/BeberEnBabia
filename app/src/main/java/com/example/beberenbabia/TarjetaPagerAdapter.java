package com.example.beberenbabia;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class TarjetaPagerAdapter extends FragmentPagerAdapter {
    private List<String> preguntas;

    public TarjetaPagerAdapter(FragmentManager fm, List<String> preguntas) {
        super(fm);
        this.preguntas = preguntas;
    }

    @Override
    public Fragment getItem(int position) {
        // Crea un fragmento para mostrar una tarjeta con la pregunta
        return TarjetaFragment.newInstance(preguntas.get(position));
    }

    @Override
    public int getCount() {
        return preguntas.size();
    }
}
