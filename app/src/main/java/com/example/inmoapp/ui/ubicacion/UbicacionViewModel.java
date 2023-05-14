package com.example.inmoapp.ui.ubicacion;



import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class UbicacionViewModel extends AndroidViewModel{

    private MutableLiveData<MapaActual> mapa;
    private Context context;
    private LatLng inmobiliaria= new LatLng(-33.30010,-66.33668);
    private LatLng yo;
    private FusedLocationProviderClient fused;


    public UbicacionViewModel (@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
        ultimaUbicacion();
    }

    public LiveData<MapaActual> getMapa(){
        if(mapa==null){
            mapa=new MutableLiveData<>();
        }
        return mapa;
    }

    public void construirMapa(){

        ultimaUbicacion();

    }

    public class MapaActual implements OnMapReadyCallback {


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(inmobiliaria).title("Inmobiliaria"));
            if(yo!=null) {
                googleMap.addMarker(new MarkerOptions().position(yo).title("Yo"));
            }
            CameraPosition camPos=new CameraPosition.Builder()
                    .target(inmobiliaria)
                    .zoom(19)
                    .bearing(45)
                    .tilt(0)
                    .build();
            CameraUpdate update= CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(update);

        }
    }

    public void ultimaUbicacion(){
        //obtener lectura de ultima localizacion
        fused = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //yo = new LatLng(location.getLatitude(),location.getLongitude());
                if (location != null){
                yo = new LatLng(location.getLatitude(),location.getLongitude());
                }
                mapa.setValue(new MapaActual());
            }
        });
    }

}