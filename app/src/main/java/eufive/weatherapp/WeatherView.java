package eufive.weatherapp;

import java.util.List;

import eufive.weatherapp.viewModel.CityWeatherViewModel;

public interface WeatherView {

    void renderData(List<CityWeatherViewModel> cities);

    void showMessage(String message);

    void navigateToDetails(CityWeatherViewModel cityWeatherViewModel);
}
