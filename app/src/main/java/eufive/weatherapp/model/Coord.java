package eufive.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Coord {

    @SerializedName("lon")
    @Expose
    private double longitude;

    @SerializedName("lat")
    @Expose
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
