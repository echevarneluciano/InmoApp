package com.example.inmoapp.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Pago;
import com.example.inmoapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class PagosContratoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> pagosMutable;

    public PagosContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> setPagos(){
        if(pagosMutable == null){
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    };

    public void getPagos(Contrato contrato){

        ArrayList<Pago> pagos = ApiClient.getApi().obtenerPagos(contrato);
        Log.d("salida","en viewmodel: "+pagos.size());
        pagosMutable.setValue(pagos);

    }
}