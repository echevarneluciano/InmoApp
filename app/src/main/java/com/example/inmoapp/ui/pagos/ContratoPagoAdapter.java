package com.example.inmoapp.ui.pagos;

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

import java.util.List;

public class ContratoPagoAdapter extends  RecyclerView.Adapter<ContratoPagoAdapter.ViewHolder> {
    private Context context;
    private List<Contrato> contratos;
    private LayoutInflater layoutInflater;

    public ContratoPagoAdapter(Context context, List<Contrato> contratos, LayoutInflater layoutInflater) {
        this.context = context;
        this.contratos = contratos;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=layoutInflater.inflate(R.layout.contrato,parent,false);
        return new ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.finicio.setText(String.valueOf(contratos.get(position).getFechaInicio()));
        holder.ffin.setText( String.valueOf(contratos.get(position).getFechaFin()) );
        holder.inquilino.setText(contratos.get(position).getInquilino().getNombre());
        holder.propiedad.setText(contratos.get(position).getInmueble().getDireccion());
        holder.monto.setText(String.valueOf(contratos.get(position).getPrecio()));

    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView finicio,ffin,inquilino,propiedad,monto;
        Button btnPago;;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inquilino=itemView.findViewById(R.id.tvCInquilino);
            propiedad=itemView.findViewById(R.id.tvCInmueble);
            monto=itemView.findViewById(R.id.tvCMonto);
            finicio=itemView.findViewById(R.id.tvCFinicio);
            ffin=itemView.findViewById(R.id.tvCFfin);
            btnPago=itemView.findViewById(R.id.btCPagos);

            btnPago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contrato", contratos.get(getAdapterPosition()));

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_content_escritorio).navigate(R.id.action_nav_pagos_to_pagosContratoFragment,bundle);
                }
            });
        }
    }


}
