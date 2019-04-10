package eufive.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public final class CityForecastList {

    @SerializedName("cnt")
    @Expose
    private int count;

    @SerializedName("list")
    @Expose
    private ArrayList<CityForecast> cityForecast;

    @SerializedName("city")
    private CityInfo cityInfo;

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public ArrayList<CityForecast> getCityForecast() {
        return cityForecast;
    }

    public int getCount() {
        return count;
    }
}

