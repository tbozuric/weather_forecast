package eufive.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public final class CityForecast {

    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather;

    @SerializedName("main")
    @Expose
    private WeatherDetails weatherDetails;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("dt_txt")
    @Expose
    private String date;

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    public Wind getWind() {
        return wind;
    }

    public String getDate() {
        return date;
    }
}
