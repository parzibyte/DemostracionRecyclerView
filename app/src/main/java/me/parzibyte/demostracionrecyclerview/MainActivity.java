package me.parzibyte.demostracionrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAgregar = findViewById(R.id.btnAgregar);
        Button btnEliminar = findViewById(R.id.btnEliminar);
        RecyclerView recyclerViewMascotas = findViewById(R.id.recyclerViewMascotas);
        final EditText editTextNombre = findViewById(R.id.editTextNombre);
        final EditText editTextEdad = findViewById(R.id.editTextEdad);
        final EditText editTextIndice = findViewById(R.id.editTextIndice);

        final AdaptadorRecyclerView adaptadorRecyclerView = new AdaptadorRecyclerView(new InterfazClickRecyclerView() {
            @Override
            public void onClick(View v, Mascota m) {
                Toast.makeText(MainActivity.this, m.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // Configuramos cómo se van a organizar las vistas dentro del RecyclerView; simplemente es un LinearLayout para que
        // aparezcan una debajo de otra
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerViewMascotas.setLayoutManager(linearLayoutManager);
        // La línea que divide los elementos
        recyclerViewMascotas.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        // El adaptador que se encarga de toda la lógica
        recyclerViewMascotas.setAdapter(adaptadorRecyclerView);
//        adaptadorRecyclerView.agregarMascota(new Mascota());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String edad = editTextEdad.getText().toString();
                if (nombre.isEmpty() || edad.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Rellena los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                adaptadorRecyclerView.agregarMascota(new Mascota(nombre, Integer.valueOf(edad)));
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indice = editTextIndice.getText().toString();
                if (indice.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Escribe el índice", Toast.LENGTH_SHORT).show();
                    return;
                }
                adaptadorRecyclerView.eliminar(Integer.valueOf(indice));
            }
        });
    }
}
