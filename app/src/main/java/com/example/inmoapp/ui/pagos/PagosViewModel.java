package com.example.inmoapp.ui.pagos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.request.ApiClient;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

public class PagosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Contrato>> contratosMutable;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Contrato>> setContratos(){
        if(contratosMutable == null){
            contratosMutable = new MutableLiveData<>();
        }
        return contratosMutable;
    };

    public void getContratos(){
        ArrayList<Contrato> contratos = new ArrayList<>();
        ArrayList<Inmueble> inmuebles = ApiClient.getApi().obtenerPropiedadesAlquiladas();

        for(Inmueble inmueble:inmuebles){
            if(inmueble.getPropietario().equals(ApiClient.getApi().obtenerUsuarioActual())){
                contratos.add(ApiClient.getApi().obtenerContratoVigente(inmueble));
            }
        }
        contratosMutable.setValue(contratos);

    }

}