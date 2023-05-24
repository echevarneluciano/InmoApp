package com.example.inmoapp.ui.inmuebles;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoapp.R;
import com.example.inmoapp.modelo.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater layoutInflater;

    public InmuebleAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater layoutInflater) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=layoutInflater.inflate(R.layout.inmueble,parent,false);
        return new  ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.direccion.setText(inmuebles.get(position).getDireccion()+"");
        String esta = inmuebles.get(position).isEstado() == 1 ? "Activo" : "Inactivo";
        holder.estado.setText(esta);
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView direccion,estado;
        ImageView imagen;
        Button btnDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion=itemView.findViewById(R.id.tvDireccion);
            estado=itemView.findViewById(R.id.tvEstado);
            imagen=itemView.findViewById(R.id.ivImagen);
            btnDetalle=itemView.findViewById(R.id.btnDetalle);

            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("inmueble", inmuebles.get(getAdapterPosition()));

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_content_escritorio).navigate(R.id.action_nav_inmuebles_to_descripcionFragment,bundle);
                }
            });
        }
    }


}
