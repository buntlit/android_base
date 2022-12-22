package com.gb.androidapponjava;

import java.io.Serializable;

public class Parcel implements Serializable {
    private String cityName;
    private int cityIndex;

    public String getCityName() {
        return cityName;
    }

    public int getCityIndex() {
        return cityIndex;
    }

    public Parcel(String cityName, int cityIndex) {
        this.cityName = cityName;
        this.cityIndex = cityIndex;
    }
}
