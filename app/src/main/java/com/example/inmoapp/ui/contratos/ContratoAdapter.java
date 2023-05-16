package com.example.inmoapp.ui.contratos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

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
        View root=layoutInflater.inflate(R.layout.contrato,parent,false);
        return new ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.direccion.setText(contratos.get(position).getInmueble().getDireccion());
        holder.nombreInquilino.setText(contratos.get(position).getInquilino().getNombre()+" "+contratos.get(position).getInquilino().getApellido());
        holder.fechai.setText(contratos.get(position).getFechaInicio());
        holder.fechaf.setText(contratos.get(position).getFechaFin());
        holder.monto.setText(String.valueOf(contratos.get(position).getMontoAlquiler()));

    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombreInquilino,direccion,fechai,fechaf,monto;
        Button btnPagos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreInquilino=itemView.findViewById(R.id.tvCInquilino);
            direccion=itemView.findViewById(R.id.tvCInmueble);
            fechai=itemView.findViewById(R.id.tvCFinicio);
            fechaf=itemView.findViewById(R.id.tvCFfin);
            monto=itemView.findViewById(R.id.tvCMonto);
            btnPagos=itemView.findViewById(R.id.btCPagos);

            btnPagos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("pagos", ApiClient.getApi().obtenerPagos(contratos.get(getAdapterPosition())));

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_content_escritorio).navigate(R.id.action_nav_contratos_to_pagosFragment,bundle);
                }
            });
        }
    }


}
