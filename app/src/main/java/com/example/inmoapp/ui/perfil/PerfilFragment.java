package com.example.inmoapp.ui.perfil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmoapp.R;
import com.example.inmoapp.databinding.FragmentPerfilBinding;
import com.example.inmoapp.request.ApiClient;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cargarPerfilInicio();

        perfilViewModel.getHabilitaEdicion().observe(getViewLifecycleOwner(), habilitaEdicion -> {
                binding.tvNombre.setEnabled(true);
                binding.tvApe.setEnabled(true);
                binding.tvMail.setEnabled(true);
                binding.tvTel.setEnabled(true);
        });

        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfilViewModel.editarPerfil();
            }
        } );

        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfilViewModel.guardarPerfil(binding.tvTel.getText().toString(), binding.tvMail.getText().toString(),
                        binding.tvNombre.getText().toString(), binding.tvApe.getText().toString());
                cargarPerfilInicio();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void cargarPerfilInicio(){

        binding.tvNombre.setText(ApiClient.getApi().obtenerUsuarioActual().getNombre());
        binding.tvNombre.setEnabled(false);
        binding.tvApe.setText(ApiClient.getApi().obtenerUsuarioActual().getApellido());
        binding.tvApe.setEnabled(false);
        binding.tvMail.setText(ApiClient.getApi().obtenerUsuarioActual().getEmail());
        binding.tvMail.setEnabled(false);
        binding.tvTel.setText(ApiClient.getApi().obtenerUsuarioActual().getTelefono());
        binding.tvTel.setEnabled(false);
        binding.ivPropietario.setImageResource(ApiClient.getApi().obtenerUsuarioActual().getAvatar());
    }


}