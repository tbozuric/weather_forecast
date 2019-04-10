package eufive.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class WeatherDetails {

    private static final double ZERO_CELSIUS_IN_KELVIN = 273.15;

    @SerializedName("temp")
    @Expose
    private double temp;

    @SerializedName("pressure")
    @Expose
    private double pressure;

    @SerializedName("humidity")
    @Expose
    private double humidity;

    @SerializedName("temp_min")
    @Expose
    private double tempMin;

    @SerializedName("temp_max")
    @Expose
    private double tempMax;

    public double getTemp() {
        return kelvinToCelsius(temp);
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return kelvinToCelsius(tempMin);
    }

    public double getTempMax() {
        return kelvinToCelsius(tempMax);
    }

    private double kelvinToCelsius(double kelvins) {

        return kelvins - ZERO_CELSIUS_IN_KELVIN;
    }
}
