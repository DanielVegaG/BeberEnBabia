package com.example.beberenbabia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PreguntaListAdapter extends RecyclerView.Adapter<PreguntaListAdapter.ViewHolder> {
    private List<String> preguntas;

    public PreguntaListAdapter(List<String> preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pregunta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String pregunta = preguntas.get(position);
        holder.textViewPregunta.setText(pregunta);
    }

    @Override
    public int getItemCount() {
        return preguntas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPregunta;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPregunta = itemView.findViewById(R.id.textViewPregunta);
        }
    }
}

