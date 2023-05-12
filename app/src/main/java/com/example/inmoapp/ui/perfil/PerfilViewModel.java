package com.example.inmoapp.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;

public class PerfilViewModel extends ViewModel {

    private  MutableLiveData<Boolean> habilitaEdicion;

    public MutableLiveData<Boolean> getHabilitaEdicion() {
        if (habilitaEdicion == null) {
            habilitaEdicion = new MutableLiveData<Boolean>();
        }
        return habilitaEdicion;
    }

    public void editarPerfil(){

        habilitaEdicion.setValue(true);

 }

 public void guardarPerfil(String tel, String mail, String nombre, String apellido){

     Propietario propietarioActual= ApiClient.getApi().obtenerUsuarioActual();

     propietarioActual.setTelefono(tel);
     propietarioActual.setEmail(mail);
     propietarioActual.setNombre(nombre);
     propietarioActual.setApellido(apellido);

     ApiClient.getApi().actualizarPerfil(propietarioActual);

 }

}