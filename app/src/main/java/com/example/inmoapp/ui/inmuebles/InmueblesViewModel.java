package com.example.inmoapp.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.request.ApiClientRetroFit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private ApiClient apiClient;
    private MutableLiveData<ArrayList<Inmueble>> propiedades;
    private Context context;
    private SharedPreferences sp;
    private String token;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        apiClient = ApiClient.getApi();
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

public LiveData<ArrayList<Inmueble>> getPropiedades(){
    if(propiedades == null){
        propiedades = new MutableLiveData<>();
    }
    return propiedades;
}

public void setPropiedades(){

    try {
        ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
        Call<List<Inmueble>> call = end.obtenerInmuebles(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    if (response.body()!=null){
                        propiedades.postValue((ArrayList<Inmueble>) response.body());// es post value por q es asincrono
                    }}else {
                    Log.d("salida2 ",response.message()+response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("salida2 ",t.getMessage());
            }
        });}catch (Exception e){
        Log.d("salida2 ",e.getMessage());
    }

}

}