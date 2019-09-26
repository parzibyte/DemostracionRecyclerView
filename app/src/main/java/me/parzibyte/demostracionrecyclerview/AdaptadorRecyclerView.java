package me.parzibyte.demostracionrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecyclerView extends RecyclerView.Adapter<ViewHolderMascota> {
    private List<Mascota> mascotas;
    private InterfazClickRecyclerView interfazClickRecyclerView;

    public AdaptadorRecyclerView(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
        this.notifyDataSetChanged();
    }

    public AdaptadorRecyclerView(InterfazClickRecyclerView interfazClickRecyclerView) {
        this.interfazClickRecyclerView = interfazClickRecyclerView;
        this.mascotas = new ArrayList<>();
    }

    public void agregarMascota(Mascota mascota) {
        this.mascotas.add(mascota);
        this.notifyItemInserted(this.mascotas.size() - 1);
    }


    public void eliminar(int indice) {
        if (indice < 0 || indice >= this.getItemCount()) return;
        this.mascotas.remove(indice);
        this.notifyItemRemoved(indice);
    }

    @NonNull
    @Override
    public ViewHolderMascota onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Esta es la vista del layout que muestra los detalles de la mascota (fila_mascota.xml)
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_mascota, parent, false);
        // Crear el viewholder a partir de esta vista. Mira la clase ViewHolderMascota si quieres
        final ViewHolderMascota viewHolder = new ViewHolderMascota(vista);
        // En el click de la vista (la mascota en general) invocamos a nuestra interfaz personalizada pasándole la vista y la mascota
        vista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfazClickRecyclerView.onClick(v, mascotas.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMascota holder, int position) {
        // Dibujar la fila de la mascota con los datos de la mascota actualmente solicitada según la variable position
        Mascota mascota = this.mascotas.get(position);
        holder.getTextViewEdad().setText(String.valueOf(mascota.getEdad()));
        holder.getTextViewNombre().setText(mascota.getNombre());
    }

    @Override
    public int getItemCount() {
        return this.mascotas.size();
    }
}
