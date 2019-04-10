package eufive.weatherapp;

import eufive.weatherapp.viewModel.CityWeatherDetailsViewModel;

public interface DetailsWeatherView {

    void renderDetailsWeather(CityWeatherDetailsViewModel cityWeatherDetailsViewModel);

    void showMessage(String message);
}
