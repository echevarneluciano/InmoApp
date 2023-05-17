package com.example.inmoapp.ui.inmuebles;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.request.ApiClient;

import java.util.ArrayList;

public class InmueblesViewModel extends AndroidViewModel {

    private ApiClient apiClient;
    private MutableLiveData<ArrayList<Inmueble>> propiedades;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        apiClient = ApiClient.getApi();
    }

public LiveData<ArrayList<Inmueble>> getPropiedades(){
    if(propiedades == null){
        propiedades = new MutableLiveData<>();
    }
    return propiedades;
}

public void setPropiedades(){

        propiedades.setValue(apiClient.obtnerPropiedades());

}

}