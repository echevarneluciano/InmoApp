package com.example.inmoapp.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmoapp.EscritorioActivity;
import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.modelo.Usuario;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.request.ApiClientRetroFit;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient api;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        api=ApiClient.getApi();
    }

    public void login(String mail, String password){
        try {
            Usuario miUsuario=new Usuario(mail,password);
            ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
            Call<String> call= end.login(miUsuario);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        if(response.body()!=null){
                            SharedPreferences sp = context.getSharedPreferences("token.xml",0);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("token","Bearer "+response.body());
                            editor.commit();
                            Intent intent = new Intent(context, EscritorioActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        }
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(context,"Error al llamar login",Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(context, "Error, informe a soporte.", Toast.LENGTH_LONG).show();
        }
    }

}
