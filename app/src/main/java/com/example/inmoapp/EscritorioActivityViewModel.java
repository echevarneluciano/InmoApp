package com.example.inmoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;

public class EscritorioActivityViewModel extends AndroidViewModel {

private ApiClient apiClient;
private MutableLiveData<Propietario> propietario;

    public EscritorioActivityViewModel(@NonNull Application application) {

        super(application);
        apiClient = new ApiClient();
    }

public LiveData<Propietario> getPropietario() {
    if (propietario == null) {
        propietario = new MutableLiveData<>();
    }
    return propietario;
}

public void setPropietario() {

       propietario.setValue(apiClient.obtenerUsuarioActual());

}

}
