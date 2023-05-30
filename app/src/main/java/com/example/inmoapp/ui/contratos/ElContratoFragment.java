package com.example.inmoapp.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmoapp.R;
import com.example.inmoapp.databinding.FragmentElContratoBinding;
import com.example.inmoapp.databinding.FragmentPagosBinding;
import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Pago;

import java.util.List;

public class ElContratoFragment extends Fragment {

    private ElContratoViewModel mViewModel;
    private FragmentElContratoBinding mBinding;

    public static ElContratoFragment newInstance() {
        return new ElContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ElContratoViewModel.class);

        mBinding = FragmentElContratoBinding.inflate(inflater, container, false);
        View root = mBinding.getRoot();

        Contrato contrato = (Contrato) getArguments().getSerializable("contrato");

        mBinding.tvCFfin.setText(String.valueOf(contrato.getFechaFin()));
        mBinding.tvCFinicio.setText( String.valueOf(contrato.getFechaInicio()));
        mBinding.tvCInmueble.setText(contrato.getInmueble().getDireccion());
        mBinding.tvCMonto.setText(String.valueOf(contrato.getPrecio()));
        mBinding.tvCInquilino.setText(contrato.getInquilino().getNombre());


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ElContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}