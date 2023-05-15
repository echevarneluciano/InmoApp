package com.example.inmoapp.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmoapp.R;
import com.example.inmoapp.databinding.FragmentInmueblesBinding;
import com.example.inmoapp.databinding.FragmentInquilinosBinding;
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.ui.inmuebles.InmuebleAdapter;
import com.example.inmoapp.ui.inmuebles.InmueblesViewModel;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinosViewModel inquilinosViewModel =
                new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.rvListaInquilinos;
        GridLayoutManager grilla = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grilla);

        inquilinosViewModel.getInquilinos();

        inquilinosViewModel.getInquilinosMutable().observe(getViewLifecycleOwner(), new Observer<List<Inquilino>>() {
            @Override
            public void onChanged(List<Inquilino> inquilinos) {
                InquilinoAdapter adapter = new InquilinoAdapter(getContext(), inquilinos, getLayoutInflater());
                recyclerView.setAdapter(adapter);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}