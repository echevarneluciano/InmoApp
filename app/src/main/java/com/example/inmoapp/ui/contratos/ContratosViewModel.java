package com.example.inmoapp.ui.contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class ContratosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Contrato>> contratosMutable;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Contrato>> getContratosMutable(){
        if(contratosMutable == null){
            contratosMutable = new MutableLiveData<>();
        }
        return contratosMutable;
    }

    public void getContratos(){

        ArrayList<Contrato> contratos = new ArrayList<>();
        ArrayList<Inmueble> inmuebles = ApiClient.getApi().obtnerPropiedades();

        for (int i = 0; i < inmuebles.size(); i++) {

            if(ApiClient.getApi().obtenerContratoVigente(inmuebles.get(i)) != null){
                contratos.add(ApiClient.getApi().obtenerContratoVigente(inmuebles.get(i)));
            }

        }

        if(contratos.size()>0){
            contratosMutable = new MutableLiveData<>(contratos);
        }

    }

}