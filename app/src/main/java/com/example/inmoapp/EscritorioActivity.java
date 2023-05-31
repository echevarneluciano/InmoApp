package com.example.inmoapp;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoapp.modelo.Propietario;
import com.example.inmoapp.request.ApiClient;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inmoapp.databinding.ActivityEscritorioBinding;

public class EscritorioActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityEscritorioBinding binding;
    private EscritorioActivityViewModel escritorioActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEscritorioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        escritorioActivity = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(EscritorioActivityViewModel.class);

        escritorioActivity.startShakeDetection();

        setSupportActionBar(binding.appBarEscritorio.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_ubicacion, R.id.nav_perfil, R.id.nav_inmuebles, R.id.nav_inquilinos, R.id.nav_contratos, R.id.nav_pagos, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_escritorio);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View navView = binding.navView.getHeaderView(0);
        ImageView imageView = (ImageView) navView.findViewById(R.id.imageView);
        TextView textViewMail = (TextView) navView.findViewById(R.id.tvMailHeader);
        TextView textViewNombre = (TextView) navView.findViewById(R.id.tvNombreHeader);

        escritorioActivity.getPropietario().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                //imageView.setImageResource(propietario.getAvatar());
                Glide.with(EscritorioActivity.this)
                        //.load("http://10.120.10.172:5200/"+propietario.getAvatar())
                        .load("http://192.168.15.31:5200/"+propietario.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
                textViewMail.setText(propietario.getEmail());
                textViewNombre.setText(propietario.getNombre());
            }
        });

        escritorioActivity.setPropietario();

        escritorioActivity.getPermissionGranted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    solicitarPermisoLlamada();
                }
            }
        });

        solicitarPermisoNavegacion();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.escritorio, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_escritorio);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void solicitarPermisoNavegacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        escritorioActivity.stopShakeDetection();
    }

    public void solicitarPermisoLlamada() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M
                &&checkSelfPermission(CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{CALL_PHONE},2000);
        }else {escritorioActivity.llamar();}
    }

}