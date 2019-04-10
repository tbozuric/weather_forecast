package eufive.weatherapp.viewModel;

public final class ForecastViewModel {

    public String nameOfCity;
    public final String date;
    public final String icon;
    public final double minimumTemperature;
    public final double maximumTemperature;
    public final double pressure;
    public final double humidity;

    public ForecastViewModel(final String nameOfCity, final String date, final String icon, final double minimumTemperature, final double maximumTemperature, final double pressure,
                             final double humidity) {
        this.nameOfCity = nameOfCity;
        this.date = date;
        this.icon = icon;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }
}
