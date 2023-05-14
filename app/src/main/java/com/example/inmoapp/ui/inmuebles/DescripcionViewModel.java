package com.example.inmoapp.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Inmueble;

public class DescripcionViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmuebleLiveData;

    public DescripcionViewModel(){};

    public LiveData<Inmueble> getInmuebleLiveData() {
        if(this.inmuebleLiveData == null) {
            this.inmuebleLiveData = new MutableLiveData<>();
        }
        return this.inmuebleLiveData;
    }

    public void setInmueble(Inmueble inmueble) {
        inmuebleLiveData.setValue(inmueble);
    }

}