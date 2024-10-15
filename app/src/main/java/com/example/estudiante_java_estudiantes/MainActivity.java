package com.example.estudiante_java_estudiantes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etAsignatura, etFecha, etCorte1, etCorte2, etCorte3;
    private Button btnAgregarEstudiante, btnMostrarEstudiantes;
    private RecyclerView recyclerViewEstudiantes;
    private EstudianteAdapter estudianteAdapter;
    private List<Estudiante> listaEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del layout
        etNombre = findViewById(R.id.etNombre);
        etAsignatura = findViewById(R.id.etAsignatura);
        etFecha = findViewById(R.id.etFecha);
        etCorte1 = findViewById(R.id.etCorte1);
        etCorte2 = findViewById(R.id.etCorte2);
        etCorte3 = findViewById(R.id.etCorte3);
        btnAgregarEstudiante = findViewById(R.id.btnAgregarEstudiante);
        btnMostrarEstudiantes = findViewById(R.id.btnMostrarEstudiantes);

        // Configurar RecyclerView
        recyclerViewEstudiantes = findViewById(R.id.recyclerViewEstudiantes);
        recyclerViewEstudiantes.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los estudiantes guardados en SharedPreferences
        listaEstudiantes = cargarEstudiantes();

        // Configurar el adaptador del RecyclerView
        estudianteAdapter = new EstudianteAdapter(listaEstudiantes);
        recyclerViewEstudiantes.setAdapter(estudianteAdapter);

        // Acción al hacer clic en "Agregar Estudiante"
        btnAgregarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEstudiante();
            }
        });

        // Actualizar la lista al hacer clic en "Mostrar Estudiantes"
        btnMostrarEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estudianteAdapter.notifyDataSetChanged();  // Recarga el RecyclerView
            }
        });
    }

    // Método para agregar un nuevo estudiante
    private void agregarEstudiante() {
        String nombre = etNombre.getText().toString();
        String asignatura = etAsignatura.getText().toString();
        String fecha = etFecha.getText().toString();
        float corte1 = Float.parseFloat(etCorte1.getText().toString());
        float corte2 = Float.parseFloat(etCorte2.getText().toString());
        float corte3 = Float.parseFloat(etCorte3.getText().toString());

        // Crear un nuevo objeto Estudiante y agregarlo a la lista
        Estudiante nuevoEstudiante = new Estudiante(nombre, asignatura, fecha, corte1, corte2, corte3);
        listaEstudiantes.add(nuevoEstudiante);  // Agregar el nuevo estudiante a la lista
        guardarEstudiantes();  // Guardar la lista actualizada en SharedPreferences

        // Notificar al adaptador que la lista ha cambiado
        estudianteAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Estudiante agregado", Toast.LENGTH_SHORT).show();
    }

    // Método para guardar la lista de estudiantes en SharedPreferences
    private void guardarEstudiantes() {
        SharedPreferences sharedPreferences = getSharedPreferences("DatosEstudiantes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listaEstudiantes);  // Convertir la lista a JSON
        editor.putString("lista_estudiantes", json);
        editor.apply();  // Guardar en SharedPreferences
    }

    // Método para cargar la lista de estudiantes desde SharedPreferences
    private List<Estudiante> cargarEstudiantes() {
        SharedPreferences sharedPreferences = getSharedPreferences("DatosEstudiantes", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("lista_estudiantes", null);
        Type type = new TypeToken<ArrayList<Estudiante>>() {}.getType();
        List<Estudiante> estudiantes = gson.fromJson(json, type);

        if (estudiantes == null) {
            estudiantes = new ArrayList<>();
        }
        return estudiantes;
    }
}
