package com.example.inmoapp.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.request.ApiClientRetroFit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {

    private Context context;
    private SharedPreferences sp;
    private String token;
    private MutableLiveData<List<Inquilino>> inquilinosMutable;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<List<Inquilino>> getInquilinosMutable(){
        if(inquilinosMutable == null){
            inquilinosMutable = new MutableLiveData<>();
        }
        return inquilinosMutable;
    }

    public void getInquilinos(){

        ArrayList<Inquilino> inquilinos = new ArrayList<>();
        ArrayList<Contrato> contratos = new ArrayList<>();

        try {
            ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
            Call<List<Contrato>> call = end.obtenerContratos(token);
            call.enqueue(new Callback<List<Contrato>>() {
                @Override
                public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                    if (response.body()!=null){
                        contratos.addAll(response.body());
                        for (Contrato contrato : contratos) {
                            inquilinos.add(contrato.getInquilino());
                        }
                        inquilinosMutable.postValue(inquilinos);
                    }
                }
                @Override
                public void onFailure(Call<List<Contrato>> call, Throwable t) {
                    Log.d("salida ",t.getMessage());
                }
            });}catch (Exception e){
            Log.d("salida ",e.getMessage());
        }
    }

}