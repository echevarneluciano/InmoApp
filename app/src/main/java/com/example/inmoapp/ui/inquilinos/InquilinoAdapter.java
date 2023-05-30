package com.example.inmoapp.ui.inquilinos;

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
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.request.ApiClient;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder> {
    private Context context;
    private List<Inquilino> inquilinos;
    private LayoutInflater layoutInflater;

    public InquilinoAdapter(Context context, List<Inquilino> inquilinos, LayoutInflater layoutInflater) {
        this.context = context;
        this.inquilinos = inquilinos;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=layoutInflater.inflate(R.layout.inquilino,parent,false);
        return new ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.dni.setText(String.valueOf(inquilinos.get(position).getDni()));
        holder.nombre.setText(inquilinos.get(position).getNombre()+"");
        holder.apellido.setText(inquilinos.get(position).getApellido()+"");

    }

    @Override
    public int getItemCount() {
        return inquilinos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,dni,apellido;
        Button btnDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dni=itemView.findViewById(R.id.tvIDni);
            nombre=itemView.findViewById(R.id.tvINombre);
            apellido=itemView.findViewById(R.id.tvIApellido);
            btnDetalle=itemView.findViewById(R.id.btIDetalles);

            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("inquilino", inquilinos.get(getAdapterPosition()));

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_content_escritorio).navigate(R.id.action_nav_inquilinos_to_detallesFragment,bundle);
                }
            });
        }
    }


}
