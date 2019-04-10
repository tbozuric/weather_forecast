package eufive.weatherapp.viewModel;

public final class CityWeatherDetailsViewModel {

    public final Integer id;
    public final String nameOfCity;
    public final double temperature;
    public final double minimumTemperature;
    public final double maximumTemperature;
    public final double windSpeed;
    public final String description;
    public final double pressure;
    public final double humidity;

    public static final CityWeatherDetailsViewModel EMPTY = new CityWeatherDetailsViewModel(-1, "", -1,
                                                                                            -1, "", -1,
                                                                                            -1, -1, -1);

    public CityWeatherDetailsViewModel(final Integer id, final String nameOfCity, final double humidity, final double pressure, final String description, final double windSpeed,
                                       final double maximumTemperature, final double minimumTemperature,
                                       final double temperature) {
        this.id = id;
        this.nameOfCity = nameOfCity;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.windSpeed = windSpeed;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.temperature = temperature;
    }
}
