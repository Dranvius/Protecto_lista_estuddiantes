package com.example.estudiante_java_estudiantes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder> {

    private List<Estudiante> estudiantes;

    public EstudianteAdapter(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, parent, false);
        return new EstudianteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteViewHolder holder, int position) {
        Estudiante estudiante = estudiantes.get(position);
        holder.tvNombre.setText(estudiante.getNombre());
        holder.tvAsignatura.setText(estudiante.getAsignatura());
        holder.tvFecha.setText(estudiante.getFecha());
        float notaFinal = (estudiante.getCorte1() * 0.3f) + (estudiante.getCorte2() * 0.3f) + (estudiante.getCorte3() * 0.4f);
        holder.tvNota.setText(String.valueOf(notaFinal));
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    public static class EstudianteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvAsignatura, tvFecha, tvNota;

        public EstudianteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvAsignatura = itemView.findViewById(R.id.tvAsignatura);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvNota = itemView.findViewById(R.id.tvNota);
        }
    }
}
