package eufive.weatherapp.presenter;

import eufive.weatherapp.ForecastView;

public interface ForecastPresenter extends BasePresenter {

    void loadForecastForCity(String city);

    void setView(ForecastView forecastView);
}
