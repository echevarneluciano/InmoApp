package com.example.inmoapp.ui.inquilinos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoapp.R;
import com.example.inmoapp.databinding.FragmentDescripcionBinding;
import com.example.inmoapp.databinding.FragmentDetallesBinding;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.ui.inmuebles.DescripcionFragment;
import com.example.inmoapp.ui.inmuebles.DescripcionViewModel;

public class DetallesFragment extends Fragment {

    private FragmentDetallesBinding binding;
    private DetallesViewModel mViewModel;
    private Inquilino inquilino;

    public static DetallesFragment newInstance() {
        return new DetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inquilino = new Inquilino();
        inquilino = (Inquilino) getArguments().getSerializable("inquilino");

        cargaDatos(inquilino);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetallesViewModel.class);
        // TODO: Use the ViewModel
    }

    public void cargaDatos(Inquilino inquilino){

        binding.tvDApellido.setText(inquilino.getApellido());
        binding.tvDNombre.setText(inquilino.getNombre());
        binding.tvDDni.setText(String.valueOf(inquilino.getDNI()));
        binding.tvDTelefono.setText(String.valueOf(inquilino.getTelefono()));
        binding.tvDEmail.setText(inquilino.getEmail());
        binding.tvDGaranteNombre.setText(inquilino.getNombreGarante());
        binding.tvDGaranteTelefono.setText(String.valueOf(inquilino.getTelefonoGarante()));
        binding.tvDTrabajo.setText(inquilino.getLugarDeTrabajo());

    }

}