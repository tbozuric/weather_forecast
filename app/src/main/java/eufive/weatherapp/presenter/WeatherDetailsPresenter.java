package eufive.weatherapp.presenter;

import eufive.weatherapp.DetailsWeatherView;

public interface WeatherDetailsPresenter extends BasePresenter {

    void citySelected(int cityId);

    void setView(DetailsWeatherView detailsWeatherView);
}
