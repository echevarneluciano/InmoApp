package com.example.inmoapp.request;

import com.example.inmoapp.R;
import com.example.inmoapp.modelo.Contrato;
import com.example.inmoapp.modelo.Inmueble;
import com.example.inmoapp.modelo.Inquilino;
import com.example.inmoapp.modelo.Pago;
import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ApiClientRetroFit {
    //private static final String PATH="http://10.120.10.172:5200/api/";//"http://localhost:5500/api/";
    private static final String PATH="http://192.168.15.31:5200/api/";
    private static  EndPointInmobiliaria endPointInmobiliaria;

    public static EndPointInmobiliaria getEndpointInmobiliaria(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endPointInmobiliaria=retrofit.create(EndPointInmobiliaria.class);

        return endPointInmobiliaria;
    }

    public interface EndPointInmobiliaria{

        @POST("Propietarios/login")
        Call<String> login(@Body Usuario user);
        //@FormUrlEncoded
        //@POST("propietarios/login")
        //Call<String> login(@Field("clave") String clave, @Field("usuario") String usuario);

        @GET("Propietarios")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @PUT("Propietarios/actualizar")
        Call<Propietario> actualizarPerfil(@Header("Authorization") String token, @Body Propietario propietario);

        //        @GET("Inmuebles/{id}") para mandarle un id como parametro (@Path("id") int id)
        @GET("Inmuebles")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

        @PUT("Inmuebles/actualizar")
        Call<Inmueble> actualizarEstado(@Header("Authorization") String token, @Body Inmueble inmueble);

        @GET("Contratos")
        Call<List<Contrato>> obtenerContratos(@Header("Authorization") String token);

        @POST("Pagos/contrato")
        Call<List<Pago>> pagosPorContrato(@Header("Authorization") String token, @Body Contrato contrato);

    }

}

