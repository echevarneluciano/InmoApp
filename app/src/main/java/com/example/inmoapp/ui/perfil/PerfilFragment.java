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

        binding.tvNombre.setText(ApiClient.getApi().obtenerUsuarioActual().getNombre());
        binding.tvApe.setText(ApiClient.getApi().obtenerUsuarioActual().getApellido());
        binding.tvMail.setText(ApiClient.getApi().obtenerUsuarioActual().getEmail());
        binding.tvTel.setText(ApiClient.getApi().obtenerUsuarioActual().getTelefono());
        binding.ivPropietario.setImageResource(ApiClient.getApi().obtenerUsuarioActual().getAvatar());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}