package eufive.weatherapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public final class CityWeatherDbModel {

    @PrimaryKey
    private int id;
    private String nameOfCity;
    private double temperature;
    private double minimumTemperature;
    private double maximumTemperature;
    private double windSpeed;
    private String description;
    private double pressure;
    private double humidity;
    private Date lastUpdated;
    private String image;

    public CityWeatherDbModel(final int id, final String description, final double humidity, final double maximumTemperature, final double minimumTemperature,
                              final double temperature, final double pressure,
                              final String nameOfCity, final double windSpeed, final Date lastUpdated, final String image) {
        this.id = id;
        this.nameOfCity = nameOfCity;
        this.temperature = temperature;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.windSpeed = windSpeed;
        this.description = description;
        this.pressure = pressure;
        this.humidity = humidity;
        this.lastUpdated = lastUpdated;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public Date getLastUpdated() {return lastUpdated;}

    public String getImage() {return image;}
}
