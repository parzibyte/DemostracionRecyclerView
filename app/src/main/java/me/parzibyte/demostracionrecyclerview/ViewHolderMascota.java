package me.parzibyte.demostracionrecyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ViewHolderMascota extends RecyclerView.ViewHolder {
    private TextView textViewEdad, textViewNombre;

    ViewHolderMascota(@NonNull View itemView) {
        super(itemView);
        textViewEdad = itemView.findViewById(R.id.textViewEdad);
        textViewNombre = itemView.findViewById(R.id.textViewNombre);
    }

    TextView getTextViewEdad() {
        return textViewEdad;
    }

    TextView getTextViewNombre() {
        return textViewNombre;
    }

}
