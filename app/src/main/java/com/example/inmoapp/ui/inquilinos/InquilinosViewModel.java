package com.example.inmoapp.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InquilinosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Inquilino>> inquilinosMutable;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Inquilino>> getInquilinosMutable(){
        if(inquilinosMutable == null){
            inquilinosMutable = new MutableLiveData<>();
        }
        return inquilinosMutable;
    }

    public void getInquilinos(){

        ArrayList<Inquilino> inquilinos = new ArrayList<>();
        ArrayList<Inmueble> inmuebles = ApiClient.getApi().obtnerPropiedades();

        for (int i = 0; i < inmuebles.size(); i++) {

            if(ApiClient.getApi().obtenerInquilino(inmuebles.get(i)) != null){
                inquilinos.add(ApiClient.getApi().obtenerInquilino(inmuebles.get(i)));
            }

        }

        if(inquilinos.size()>0){
            inquilinosMutable = new MutableLiveData<>(inquilinos);
        }

    }

}