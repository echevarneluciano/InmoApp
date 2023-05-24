package com.example.inmoapp.ui.contratos;

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
import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.request.ApiClient;

import java.util.List;

public class ContratoAdapter extends  RecyclerView.Adapter<ContratoAdapter.ViewHolder> {
    private Context context;
    private List<Contrato> contratos;
    private LayoutInflater layoutInflater;

    public ContratoAdapter(Context context, List<Contrato> contratos, LayoutInflater layoutInflater) {
        this.context = context;
        this.contratos = contratos;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=layoutInflater.inflate(R.layout.contratoinmueble,parent,false);
        return new ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.direccion.setText(contratos.get(position).getInmueble().getDireccion()+"");
        String esta = contratos.get(position).getInmueble().isEstado() == 1 ? "Activo" : "Inactivo";
        holder.estado.setText(esta);
        Glide.with(context)
                .load(contratos.get(position).getInmueble().getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView direccion,estado;
        ImageView imagen;
        Button btnContrato;;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion=itemView.findViewById(R.id.tvDireccionC);
            estado=itemView.findViewById(R.id.tvEstadoC);
            imagen=itemView.findViewById(R.id.ivImagenC);
            btnContrato=itemView.findViewById(R.id.btnDetalleC);

            btnContrato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contrato", contratos.get(getAdapterPosition()));

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_content_escritorio).navigate(R.id.action_nav_contratos_to_elContratoFragment,bundle);
                }
            });
        }
    }


}
