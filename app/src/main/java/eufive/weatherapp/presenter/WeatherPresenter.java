package eufive.weatherapp.presenter;

import eufive.weatherapp.WeatherView;
import eufive.weatherapp.viewModel.CityWeatherViewModel;

public interface WeatherPresenter extends BasePresenter {

    void itemSelected(CityWeatherViewModel cityWeatherViewModel);

    void loadWeatherForCities();

    void setWeatherView(WeatherView weatherView);
}
