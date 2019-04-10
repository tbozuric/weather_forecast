package eufive.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public final class CitiesList {

    @SerializedName("cnt")
    @Expose
    private int count;

    @SerializedName("list")
    @Expose
    private ArrayList<City> cities;

    public int getConut() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(final ArrayList<City> cities) {
        this.cities = cities;
    }
}
