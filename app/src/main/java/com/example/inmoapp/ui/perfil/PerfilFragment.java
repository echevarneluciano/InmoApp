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
import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        perfilViewModel.getHabilitaEdicion().observe(getViewLifecycleOwner(), habilitaEdicion -> {
                binding.tvNombre.setEnabled(true);
                binding.tvApe.setEnabled(true);
                binding.tvMail.setEnabled(true);
                binding.tvTel.setEnabled(true);
        });

        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), propietario -> {
            cargarPerfilInicio(propietario);
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
              // perfilViewModel.cargarPerfilInicioVm();
            }
        });

        perfilViewModel.cargarPerfilInicioVm();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void cargarPerfilInicio(Propietario pro){

        binding.tvNombre.setText(pro.getNombre());
        binding.tvNombre.setEnabled(false);
        binding.tvApe.setText(pro.getApellido());
        binding.tvApe.setEnabled(false);
        binding.tvMail.setText(pro.getEmail());
        binding.tvMail.setEnabled(false);
        binding.tvTel.setText(pro.getTelefono());
        binding.tvTel.setEnabled(false);
        binding.ivPropietario.setImageResource(pro.getAvatar());
    }


}