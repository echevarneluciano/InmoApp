package com.example.inmoapp.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClientRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescripcionViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleLiveData;
    private Context context;
    private SharedPreferences sp;
    private String token;

    public DescripcionViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }


    public LiveData<Inmueble> getInmuebleLiveData() {
        if(this.inmuebleLiveData == null) {
            this.inmuebleLiveData = new MutableLiveData<>();
        }
        return this.inmuebleLiveData;
    }

    public void setInmueble(Inmueble inmueble) {
        inmuebleLiveData.setValue(inmueble);
    }

    public void cambiaEstado(Inmueble inmueble) {

        try {
            ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
            Call<Inmueble> call = end.actualizarEstado(token,inmueble);
            call.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if(response.isSuccessful()){
                        if (response.body()!=null){
                            Toast.makeText(context, "Estado actualizado", Toast.LENGTH_SHORT).show();
                        }}else {
                        Log.d("salida2 ",response.message()+response.code());
                    }
                }
                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Log.d("salida2 ",t.getMessage());
                }
            });}catch (Exception e){
            Log.d("salida2 ",e.getMessage());
        }

    }

}