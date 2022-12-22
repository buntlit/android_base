package com.gb.androidapponjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class ShowWeatherFragment extends Fragment {
    public static final String PARCEL = "PARCEL";

    static ChooseCityPresenter presenter = ChooseCityPresenter.getInstance();

    static LinearLayout humidityLinear;
    static LinearLayout pressureLinear;
    static LinearLayout windSpeedLinear;


    public static ShowWeatherFragment create(Parcel parcel) {
        ShowWeatherFragment showWeatherFragment = new ShowWeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        showWeatherFragment.setArguments(args);
        return showWeatherFragment;
    }

    public Parcel getParcel() {
        Serializable savedParcel = getArguments().getSerializable(PARCEL);
        if (savedParcel instanceof Parcel) {
            return (Parcel) savedParcel;
        } else {
            return new Parcel(getResources().getStringArray(R.array.cities)[0], 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView city = view.findViewById(R.id.city);
        TextView temperatureValue = view.findViewById(R.id.temperatureValue);
        TextView humidityValue = view.findViewById(R.id.humidityValue);
        TextView pressureValue = view.findViewById(R.id.pressureValue);
        TextView windSpeedValue = view.findViewById(R.id.windSpeedValue);

        humidityLinear = view.findViewById(R.id.humidityLinear);
        pressureLinear = view.findViewById(R.id.pressureLinear);
        windSpeedLinear = view.findViewById(R.id.windSpeedLinear);

        String[] temperaturesArray = getResources().getStringArray(R.array.temperature);
        String[] humidityArray = getResources().getStringArray(R.array.humidity);
        String[] pressureArray = getResources().getStringArray(R.array.pressure);
        String[] windSpeedArray = getResources().getStringArray(R.array.windSpeed);

        Parcel parcel = getParcel();
        int index = parcel.getCityIndex();
        city.setText(parcel.getCityName());
        temperatureValue.setText(temperaturesArray[index]);
        humidityValue.setText(humidityArray[index]);
        pressureValue.setText(pressureArray[index]);
        windSpeedValue.setText(windSpeedArray[index]);

        showExtras();
        Button settingsButton = view.findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });
        Button showWeatherOnInternet = view.findViewById(R.id.showWeatherOnInternet);
        showWeatherOnInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse("https://yandex.ru/pogoda/korolev");
                startActivity(new Intent(Intent.ACTION_VIEW, address));
            }
        });
    }

    public static void showExtras() {
        boolean isCheckedHumidity = presenter.getIsCheckBoxHumidity();
        boolean isCheckedPressure = presenter.getIsCheckBoxPressure();
        boolean isCheckedWindSpeed = presenter.getIsCheckBoxWindSpeed();
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
    }
}
