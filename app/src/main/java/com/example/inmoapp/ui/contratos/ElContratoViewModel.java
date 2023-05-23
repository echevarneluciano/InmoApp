package com.example.inmoapp.ui.contratos;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.inmoapp.R;
import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Pago;
import com.example.inmoapp.request.ApiClient;

import java.io.Serializable;
import java.util.List;

public class ElContratoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Pago>> pagosMutable;
    private Context context;

    public ElContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getPagosMutable(){
        if(pagosMutable == null){
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    }

    public void getPagos(Contrato contrato){
       List<Pago> pagos = ApiClient.getApi().obtenerPagos(contrato);

       pagosMutable.setValue(pagos);

    }


}