package com.example.inmoapp.ui.inmuebles;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoapp.R;
import com.example.inmoapp.databinding.FragmentDescripcionBinding;
import com.example.inmoapp.databinding.FragmentInmueblesBinding;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.request.ApiClient;

public class DescripcionFragment extends Fragment {

    private FragmentDescripcionBinding binding;
    private DescripcionViewModel mViewModel;
    private Inmueble inmueble;

    public static DescripcionFragment newInstance() {
        return new DescripcionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DescripcionViewModel.class);

        binding = FragmentDescripcionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inmueble = new Inmueble();
        inmueble = (Inmueble) getArguments().getSerializable("inmueble");

        cargaDatos(inmueble);

        binding.swDActivo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int estado = isChecked ? 1 : 0;
            mViewModel.cambiaEstado(inmueble);
            inmueble.setEstado(estado);
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DescripcionViewModel.class);
        // TODO: Use the ViewModel
    }

    public void cargaDatos(Inmueble inmueble){

        boolean check = inmueble.isEstado() == 1 ? true : false;

        binding.tvDDireccion.setText(inmueble.getDireccion());
        binding.tvDUso.setText(inmueble.getUso());
        binding.tvDTipo.setText(inmueble.getTipo());
        binding.tvDAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
        binding.tvDPrecio.setText(String.valueOf(inmueble.getPrecio()));
        binding.swDActivo.setChecked(check);

        Glide.with(getContext())
                .load(inmueble.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivDetalle);

    }

}