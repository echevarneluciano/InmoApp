package com.example.inmoapp.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.request.ApiClientRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private  MutableLiveData<Boolean> habilitaEdicion;
    private ApiClient apiClient;
    private MutableLiveData<Propietario> propietario;
    private Context context;
    private SharedPreferences sp;
    private String token;
    private Propietario propietarioActual;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        apiClient = new ApiClient();
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
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

     Propietario propietarioUpdate= propietarioActual;

     propietarioUpdate.setTelefono(tel);
     propietarioUpdate.setEmail(mail);
     propietarioUpdate.setNombre(nombre);
     propietarioUpdate.setApellido(apellido);

     try {
         ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
         Call<Propietario> call = end.actualizarPerfil(token,propietarioUpdate);
         call.enqueue(new Callback<Propietario>() {
             @Override
             public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                 if(response.isSuccessful()){


                 if (response.body()!=null){
                     Log.d("salida2 ",response.body().getNombre());
                     propietario.postValue(response.body());// es post value por q es asincrono
                     propietarioActual=response.body();
                 }}else {
                     Log.d("salida2 ",response.message()+response.code());
                 }
             }
             @Override
             public void onFailure(Call<Propietario> call, Throwable t) {
                 Log.d("salida2 ",t.getMessage());
             }
         });}catch (Exception e){
         Log.d("salida2 ",e.getMessage());
     }

 }

 public void cargarPerfilInicioVm(){

     try {
         ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
         Call<Propietario> call = end.obtenerPerfil(token);
         call.enqueue(new Callback<Propietario>() {
             @Override
             public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                 if (response.body()!=null){
                     Log.d("salida ",response.body().getNombre());
                     propietario.postValue(response.body());// es post value por q es asincrono
                     propietarioActual=response.body();
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