package com.gb.androidapponjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String instanceState;
        name = " - onCreate";
        Button settingsButton = findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
//        if (savedInstanceState == null) {
//            instanceState = "First run";
//        } else {
//            instanceState = "Rerun";
//        }
//        Toast.makeText(getApplicationContext(), TAG + " " + instanceState + name, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, instanceState + name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        name = "onStart";
//        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, name);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        name = "Rerun - onRestoreInstanceState";
//        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        name = "onStart";
//        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, name);
    }

    @Override
    protected void onPause() {
        super.onPause();
        name = "onPause";
        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
        Log.d(TAG, name);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        name = "onSaveInstanceState";
//        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, name);
    }

    @Override
    protected void onStop() {
        super.onStop();
        name = "onStop";
        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
        Log.d(TAG, name);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        name = "onRestart";
//        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        name = "onDestroy";
        Toast.makeText(getApplicationContext(), TAG + " " + name, Toast.LENGTH_SHORT).show();
        Log.d(TAG, name);
    }
}