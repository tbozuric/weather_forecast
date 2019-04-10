package eufive.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;

    @SerializedName("deg")
    @Expose
    private double degrees;

    public double getSpeed() {
        return speed;
    }

    public double getDegrees() {
        return degrees;
    }
}
