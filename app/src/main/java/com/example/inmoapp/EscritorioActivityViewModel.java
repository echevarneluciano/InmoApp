package com.example.inmoapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.request.ApiClientRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscritorioActivityViewModel extends AndroidViewModel {

private ApiClient apiClient;
private Context context;
private SharedPreferences sp;
private String token;
private MutableLiveData<Propietario> propietario;

    public EscritorioActivityViewModel(@NonNull Application application) {
        super(application);
        apiClient = new ApiClient();
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

public LiveData<Propietario> getPropietario() {
    if (propietario == null) {
        propietario = new MutableLiveData<>();
    }
    return propietario;
}

public void setPropietario() {
        try {
    ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
    Call<Propietario> call = end.obtenerPerfil(token);
    call.enqueue(new Callback<Propietario>() {
        @Override
        public void onResponse(Call<Propietario> call, Response<Propietario> response) {
            if (response.body()!=null){
                Log.d("salida ",response.body().getNombre());
                propietario.postValue(response.body());// es post value por q es asincrono
            }
        }
        @Override
        public void onFailure(Call<Propietario> call, Throwable t) {
            Log.d("salida ",t.getMessage());
        }
    });}catch (Exception e){
        Log.d("salida ",e.getMessage());
    }
}

}
