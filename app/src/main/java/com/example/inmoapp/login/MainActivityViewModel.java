package com.example.inmoapp.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmoapp.EscritorioActivity;
import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;

import java.io.Serializable;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient api;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        api=ApiClient.getApi();
    }

    public void login(String mail, String password){
        Propietario propietario = api.login(mail,password);
        if(propietario!=null){
            Intent intent = new Intent(context, EscritorioActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }

    }

}
