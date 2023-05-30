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
import com.example.inmoapp.modelo.Pago;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.request.ApiClientRetroFit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosContratoViewModel extends AndroidViewModel {
    private Context context;
    private SharedPreferences sp;
    private String token;
    private MutableLiveData<List<Pago>> pagosMutable;

    public PagosContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<List<Pago>> setPagos(){
        if(pagosMutable == null){
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    };

    public void getPagos(Contrato contrato){

        ArrayList<Pago> pagos = new ArrayList<>();

        try {
            ApiClientRetroFit.EndPointInmobiliaria end = ApiClientRetroFit.getEndpointInmobiliaria();
            Call<List<Pago>> call = end.pagosPorContrato(token,contrato);
            Log.d("salida ", call.request().toString());
            call.enqueue(new Callback<List<Pago>>() {
                @Override
                public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                        if (response.body() != null) {
                            pagosMutable.setValue(response.body());
                        }
                    }
                @Override
                public void onFailure(Call<List<Pago>> call, Throwable t) {
                    Log.d("salida ", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida ", e.getMessage());
        }
    }
}