package com.theost.surfaceapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.theost.surfaceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //binding.mySurfaceView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.mySurfaceView.stop();
    }

}