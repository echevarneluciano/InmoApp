package com.example.inmoapp.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmoapp.EscritorioActivity;
import com.example.inmoapp.databinding.FragmentInmueblesBinding;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.request.ApiClient;

import java.util.ArrayList;


public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InmueblesViewModel inmueblesViewModel =
                new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.rvListaInmuebles;
        GridLayoutManager grilla = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grilla);

        inmueblesViewModel.getPropiedades().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
        @Override
        public void onChanged(ArrayList<Inmueble> inmuebles) {
            InmuebleAdapter adapter = new InmuebleAdapter(getContext(), inmuebles, getLayoutInflater());
            recyclerView.setAdapter(adapter);
        }
        });

        inmueblesViewModel.setPropiedades();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}