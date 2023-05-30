package com.example.inmoapp.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.request.ApiClientRetroFit;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {

    private Context context;
    private SharedPreferences sp;
    private String token;
    private MutableLiveData<List<Contrato>> contratosMutable;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<List<Contrato>> setContratos(){
        if(contratosMutable == null){
            contratosMutable = new MutableLiveData<>();
        }
        return contratosMutable;
    };

    public void getContratos() {

        try {
            ApiClientRetroFit.EndPointInmobiliaria end = ApiClientRetroFit.getEndpointInmobiliaria();
            Call<List<Contrato>> call = end.obtenerContratos(token);
            call.enqueue(new Callback<List<Contrato>>() {
                @Override
                public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                    if (response.body() != null) {
                        contratosMutable.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Contrato>> call, Throwable t) {
                    Log.d("salida ", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida ", e.getMessage());
        }
    }

}