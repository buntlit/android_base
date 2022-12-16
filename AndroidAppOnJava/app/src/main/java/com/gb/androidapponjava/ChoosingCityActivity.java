package com.gb.androidapponjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChoosingCityActivity extends AppCompatActivity {

    final ChooseCityPresenter presenter = ChooseCityPresenter.getInstance();
    final String CITY = "CITY";
    final String HUMIDITY = "HUMIDITY";
    final String PRESSURE = "PRESSURE";
    final String WIND_SPEED  ="WIND_SPEED";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        EditText enterCity = findViewById(R.id.enterCity);
        CheckBox checkBoxHumidity = findViewById(R.id.checkBoxHumidity);
        CheckBox checkBoxPressure = findViewById(R.id.checkBoxPressure);
        CheckBox checkBoxWindSpeed = findViewById(R.id.checkBoxWindSpeed);
        restoreInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);

        TextView textViewCity1 = findViewById(R.id.city1);
        textViewCity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = textViewCity1.getText().toString();
                showWeather(city, checkBoxHumidity.isChecked(), checkBoxPressure.isChecked(), checkBoxWindSpeed.isChecked());
            }
        });
        TextView textViewCity2 = findViewById(R.id.city2);
        textViewCity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = textViewCity2.getText().toString();
                showWeather(city, checkBoxHumidity.isChecked(), checkBoxPressure.isChecked(), checkBoxWindSpeed.isChecked());
            }
        });
        TextView textViewCity3 = findViewById(R.id.city3);
        textViewCity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = textViewCity3.getText().toString();
                showWeather(city, checkBoxHumidity.isChecked(), checkBoxPressure.isChecked(), checkBoxWindSpeed.isChecked());
            }
        });

        Button showWeatherButton = findViewById(R.id.buttonShowWeather);
        showWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = enterCity.getText().toString();
                showWeather(city, checkBoxHumidity.isChecked(), checkBoxPressure.isChecked(), checkBoxWindSpeed.isChecked());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        EditText enterCity = findViewById(R.id.enterCity);
        CheckBox checkBoxHumidity = findViewById(R.id.checkBoxHumidity);
        CheckBox checkBoxPressure = findViewById(R.id.checkBoxPressure);
        CheckBox checkBoxWindSpeed = findViewById(R.id.checkBoxWindSpeed);
        saveInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EditText enterCity = findViewById(R.id.enterCity);
        CheckBox checkBoxHumidity = findViewById(R.id.checkBoxHumidity);
        CheckBox checkBoxPressure = findViewById(R.id.checkBoxPressure);
        CheckBox checkBoxWindSpeed = findViewById(R.id.checkBoxWindSpeed);
        restoreInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);
    }

    private void saveInstance(EditText enterCity, CheckBox checkBoxHumidity, CheckBox checkBoxPressure, CheckBox checkBoxWindSpeed) {
        presenter.setEnterCity(enterCity.getText().toString());
        presenter.setCheckBoxHumidity(checkBoxHumidity.isChecked());
        presenter.setCheckBoxPressure(checkBoxPressure.isChecked());
        presenter.setCheckBoxWindSpeed(checkBoxWindSpeed.isChecked());
    }

    private void restoreInstance(EditText enterCity, CheckBox checkBoxHumidity, CheckBox checkBoxPressure, CheckBox checkBoxWindSpeed) {
        enterCity.setText(presenter.getEnterCity());
        checkBoxHumidity.setChecked(presenter.getIsCheckBoxHumidity());
        checkBoxPressure.setChecked(presenter.getIsCheckBoxPressure());
        checkBoxWindSpeed.setChecked(presenter.getIsCheckBoxWindSpeed());
    }

    private void showWeather(String city, boolean isCheckedHumidity, boolean isCheckedPressure, boolean isCheckedWindSpeed){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(CITY, city);
        intent.putExtra(HUMIDITY, isCheckedHumidity);
        intent.putExtra(PRESSURE, isCheckedPressure);
        intent.putExtra(WIND_SPEED, isCheckedWindSpeed);
        startActivity(intent);
    }
}
