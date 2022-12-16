package com.gb.androidapponjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChoosingCityActivity extends AppCompatActivity {

    final ChooseCityPresenter presenter = ChooseCityPresenter.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        EditText enterCity = findViewById(R.id.enterCity);
        CheckBox checkBoxHumidity = findViewById(R.id.checkBoxHumidity);
        CheckBox checkBoxPressure = findViewById(R.id.checkBoxPressure);
        CheckBox checkBoxWindSpeed = findViewById(R.id.checkBoxWindSpeed);
        restoreInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);
        Button showWeatherButton = findViewById(R.id.buttonShowWeather);
        showWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setEnterCity(enterCity.getText().toString());
                startActivity(new Intent(ChoosingCityActivity.this, MainActivity.class));
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
}
