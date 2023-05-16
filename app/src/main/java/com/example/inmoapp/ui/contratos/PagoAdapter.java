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
import com.example.inmoapp.modelo.Pago;
import com.example.inmoapp.request.ApiClient;

import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {

    private Context context;
    private List<Pago> pagos;
    private LayoutInflater layoutInflater;

    public PagoAdapter(Context context, List<Pago> pagos, LayoutInflater layoutInflater) {
        this.context = context;
        this.pagos = pagos;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public PagoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=layoutInflater.inflate(R.layout.pago,parent,false);
        return new PagoAdapter.ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.ViewHolder holder, int position) {

        holder.contratoPago.setText(pagos.get(position).getContrato().getInmueble().getDireccion());
        holder.numPago.setText(String.valueOf(pagos.get(position).getNumero()));
        holder.fechaPago.setText(pagos.get(position).getFechaDePago());
        holder.montoPago.setText(String.valueOf(pagos.get(position).getImporte()));

    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView numPago,fechaPago,montoPago,contratoPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numPago = itemView.findViewById(R.id.tvPnumero);
            fechaPago = itemView.findViewById(R.id.tvPFecha);
            montoPago = itemView.findViewById(R.id.tvPImporte);
            contratoPago = itemView.findViewById(R.id.tvPContrato);

        }
    }

}
