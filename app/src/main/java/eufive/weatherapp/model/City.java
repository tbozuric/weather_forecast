package eufive.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public final class City {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("coord")
    @Expose
    private Coord cord;

    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather;

    @SerializedName("main")
    @Expose
    private WeatherDetails weatherDetails;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Coord getCord() {
        return cord;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    public Wind getWind() {
        return wind;
    }
}

