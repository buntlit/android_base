package com.gb.androidapponjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    final String TAG = "MainActivity";
    final String CITY = "CITY";
    final String HUMIDITY = "HUMIDITY";
    final String PRESSURE = "PRESSURE";
    final String WIND_SPEED = "WIND_SPEED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = " - onCreate";
        restoreInstance();
        Button settingsButton = findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
        Button showWeatherOnInternet = findViewById(R.id.showWeatherOnInternet);
        showWeatherOnInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse("https://yandex.ru/pogoda/korolev");
                startActivity(new Intent(Intent.ACTION_VIEW, address));
            }
        });

//        String instanceState;
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

    private void restoreInstance() {
        String city = getIntent().getExtras().getString(CITY);
        boolean isCheckedHumidity = getIntent().getExtras().getBoolean(HUMIDITY);
        boolean isCheckedPressure = getIntent().getExtras().getBoolean(PRESSURE);
        boolean isCheckedWindSpeed = getIntent().getExtras().getBoolean(WIND_SPEED);
        TextView textViewCity = findViewById(R.id.city);
        LinearLayout humidityLinear = findViewById(R.id.humidityLinear);
        LinearLayout pressureLinear = findViewById(R.id.pressureLinear);
        LinearLayout windSpeedLinear = findViewById(R.id.windSpeedLinear);
        if (isCheckedHumidity) {
            humidityLinear.setVisibility(View.VISIBLE);
        } else {
            humidityLinear.setVisibility(View.GONE);
        }
        if (isCheckedPressure) {
            pressureLinear.setVisibility(View.VISIBLE);
        } else {
            pressureLinear.setVisibility(View.GONE);
        }
        if (isCheckedWindSpeed) {
            windSpeedLinear.setVisibility(View.VISIBLE);
        } else {
            windSpeedLinear.setVisibility(View.GONE);
        }
        textViewCity.setText(city);
    }
}