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

public class PerfilViewModel extends AndroidViewModel {

    private  MutableLiveData<Boolean> habilitaEdicion;
    private ApiClient apiClient;
    private MutableLiveData<Propietario> propietario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        apiClient = new ApiClient();
    }

    public LiveData<Boolean> getHabilitaEdicion() {
        if (habilitaEdicion == null) {
            habilitaEdicion = new MutableLiveData<Boolean>();
        }
        return habilitaEdicion;
    }

    public LiveData<Propietario> getPropietario() {
        if (propietario == null) {
            propietario = new MutableLiveData<Propietario>();
        }
        return propietario;
    }

    public void editarPerfil(){

        habilitaEdicion.setValue(true);

 }

 public void guardarPerfil(String tel, String mail, String nombre, String apellido){

     Propietario propietarioActual= apiClient.obtenerUsuarioActual();

     propietarioActual.setTelefono(tel);
     propietarioActual.setEmail(mail);
     propietarioActual.setNombre(nombre);
     propietarioActual.setApellido(apellido);

     ApiClient.getApi().actualizarPerfil(propietarioActual);

 }

 public void cargarPerfilInicioVm(){

        propietario.setValue(apiClient.obtenerUsuarioActual());

 }

}