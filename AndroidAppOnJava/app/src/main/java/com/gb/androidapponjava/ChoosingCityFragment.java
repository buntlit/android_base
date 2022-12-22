package com.gb.androidapponjava;

import static com.gb.androidapponjava.ShowWeatherFragment.PARCEL;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.Serializable;

public class ChoosingCityFragment extends Fragment {

    final String CURRENT_CITY = "CURRENT_CITY";
    final int DEFAULT_INDEX = 5;

    static ChooseCityPresenter presenter = ChooseCityPresenter.getInstance();

    boolean isLandscape;
    Parcel currentParcel;
    EditText enterCity;
    CheckBox checkBoxHumidity;
    CheckBox checkBoxPressure;
    CheckBox checkBoxWindSpeed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
        enterCity = view.findViewById(R.id.enterCity);
        checkBoxHumidity = view.findViewById(R.id.checkBoxHumidity);
        checkBoxPressure = view.findViewById(R.id.checkBoxPressure);
        checkBoxWindSpeed = view.findViewById(R.id.checkBoxWindSpeed);
        checkBoxHumidity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               showExtras();
            }
        });
        checkBoxPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showExtras();
            }
        });
        checkBoxWindSpeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showExtras();
            }
        });

        restoreInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);
        Button showWeatherButton = view.findViewById(R.id.buttonShowWeather);
        showWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Parcel enterParcel = new Parcel(enterCity.getText().toString(), DEFAULT_INDEX);
                showWeather(enterParcel);
            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        currentParcel = new Parcel(getResources().getStringArray(R.array.cities)[0], 0);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            Serializable savedParcel = savedInstanceState.getSerializable(CURRENT_CITY);
            if (savedParcel instanceof Parcel) {
                currentParcel = (Parcel) savedParcel;
            }
        }
        if (isLandscape) {
            showWeather(currentParcel);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(CURRENT_CITY, currentParcel);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        saveInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);
    }

    @Override
    public void onResume() {
        super.onResume();
        restoreInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);
    }

    private void initList(View view) {
        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        String[] cities = getResources().getStringArray(R.array.cities);

        for (int i = 0; i < cities.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(cities[i]);
            textView.setTextSize(20);
            linearLayout.addView(textView);
            final int index = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentParcel = new Parcel(getResources().getStringArray(R.array.cities)[index], index);
                    showWeather(currentParcel);
                }
            });
        }
    }

    private void showWeather(Parcel currentParcel) {
        if (isLandscape) {
            ShowWeatherFragment showWeatherFragment = (ShowWeatherFragment) getFragmentManager().findFragmentById(R.id.weather);
            if (showWeatherFragment == null || showWeatherFragment.getParcel().getCityIndex() != currentParcel.getCityIndex()) {
                showWeatherFragment = ShowWeatherFragment.create(currentParcel);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.weather, showWeatherFragment);
                fragmentTransaction.commit();
            }
        } else {
            Intent intent = new Intent(getContext(), ShowWeatherActivity.class);
            intent.putExtra(PARCEL, currentParcel);
            startActivity(intent);
        }
    }

    private void showExtras() {
        saveInstance(enterCity, checkBoxHumidity, checkBoxPressure, checkBoxWindSpeed);
        ShowWeatherFragment.showExtras();
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
