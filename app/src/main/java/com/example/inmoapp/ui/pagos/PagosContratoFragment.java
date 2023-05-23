package com.example.inmoapp.ui.pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmoapp.R;
import com.example.inmoapp.databinding.FragmentPagosBinding;
import com.example.inmoapp.databinding.FragmentPagosContratoBinding;
import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagosContratoFragment extends Fragment {

    private PagosContratoViewModel mViewModel;
    private FragmentPagosContratoBinding binding;

    public static PagosContratoFragment newInstance() {
        return new PagosContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PagosContratoViewModel.class);
        binding = FragmentPagosContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.rvListaPagosContrato;
        GridLayoutManager grilla = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grilla);

        mViewModel.setPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                PagoAdapter adapter = new PagoAdapter(getContext(),  pagos, getLayoutInflater());
                recyclerView.setAdapter(adapter);
            }
        });

        Contrato contrato = (Contrato) getArguments().getSerializable("contrato");

        mViewModel.getPagos(contrato);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PagosContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}