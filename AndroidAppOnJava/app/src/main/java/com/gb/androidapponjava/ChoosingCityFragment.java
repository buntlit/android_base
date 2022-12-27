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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChoosingCityFragment extends Fragment {

    final String CURRENT_CITY = "CURRENT_CITY";
    final String LIST = "LIST";

    static ChooseCityPresenter presenter = ChooseCityPresenter.getInstance();

    boolean isLandscape;
    Parcel currentParcel;
    EditText enterCity;
    CheckBox checkBoxHumidity;
    CheckBox checkBoxPressure;
    CheckBox checkBoxWindSpeed;
    final CitiesAdapter adapter = new CitiesAdapter();
    List<String> cities;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            cities = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.cities)));
        } else {
            cities = savedInstanceState.getStringArrayList(LIST);
        }
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
                String text = enterCity.getText().toString();
                if (!text.equals("")) {
                    if (!cities.contains(text)) {
                        cities.add(cities.size(), text);
                        adapter.setCities(cities);
                        currentParcel.setCities(cities);
                        adapter.notifyItemInserted(cities.size() - 1);

                    }
                    Parcel enterParcel = new Parcel(text, cities.size() - 1, cities);
                    showWeather(enterParcel);
                }
            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            Serializable savedParcel = savedInstanceState.getSerializable(CURRENT_CITY);
            if (savedParcel instanceof Parcel) {
                currentParcel = (Parcel) savedParcel;
            }
        } else {
            currentParcel = new Parcel(cities.get(0), 0, cities);
        }
        if (isLandscape) {
            showWeather(currentParcel);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(CURRENT_CITY, currentParcel);
        outState.putStringArrayList(LIST, (ArrayList<String>) cities);
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
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setCities(cities);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CitiesAdapter.OnItemClickListener() {
            @Override
            public void onCityTextClick(View view, int position) {
                currentParcel = new Parcel(cities.get(position), position, cities);
                showWeather(currentParcel);
            }
        });
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
