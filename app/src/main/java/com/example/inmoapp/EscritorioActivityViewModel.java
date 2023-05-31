package com.example.inmoapp;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;
import com.example.inmoapp.request.ApiClientRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscritorioActivityViewModel extends AndroidViewModel {

private ApiClient apiClient;
private Context context;
private SharedPreferences sp;
private String token;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeListener shakeListener;
    private MutableLiveData<Boolean> permissionGranted;
private MutableLiveData<Propietario> propietario;

    public EscritorioActivityViewModel(@NonNull Application application) {
        super(application);
        apiClient = new ApiClient();
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<Boolean> getPermissionGranted() {
        if (permissionGranted == null) {
            permissionGranted = new MutableLiveData<>();
        }
        return permissionGranted;
    }

public LiveData<Propietario> getPropietario() {
    if (propietario == null) {
        propietario = new MutableLiveData<>();
    }
    return propietario;
}

public void setPropietario() {
        try {
    ApiClientRetroFit.EndPointInmobiliaria end=ApiClientRetroFit.getEndpointInmobiliaria();
    Call<Propietario> call = end.obtenerPerfil(token);
    call.enqueue(new Callback<Propietario>() {
        @Override
        public void onResponse(Call<Propietario> call, Response<Propietario> response) {
            if (response.body()!=null){
                Log.d("salida ",response.body().getNombre());
                propietario.postValue(response.body());// es post value por q es asincrono
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


    private class ShakeListener implements SensorEventListener {
        private static final float SHAKE_THRESHOLD = 800f;
        private long lastUpdate = 0;
        private float last_x, last_y, last_z;

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                long curTime = System.currentTimeMillis();
                if ((curTime - lastUpdate) > 200) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;
                    float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;
                    if (speed > SHAKE_THRESHOLD) {
                        Log.d("salida ", "Shake detected");
                        permissionGranted.postValue(true);
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // No se utiliza
        }
    }

    public void startShakeDetection() {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            shakeListener = new ShakeListener();
            sensorManager.registerListener(shakeListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d("salida ", "Sensor registrado");
        }
    }

    public void stopShakeDetection() {
        if (sensorManager != null && shakeListener != null) {
            sensorManager.unregisterListener(shakeListener);
        }
    }

    private void makePhoneCall() {
        String phoneNumber = "123456789"; // Número de teléfono al que se desea llamar
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    public void llamar() {
            makePhoneCall();
    }

}
