package eufive.weatherapp.dagger.fragment;

import eufive.weatherapp.ForecastFragment;
import eufive.weatherapp.WeatherFragment;
import eufive.weatherapp.presenter.ForecastPresenter;
import eufive.weatherapp.presenter.WeatherPresenter;

public interface FragmentComponentInjects {

    void inject(WeatherPresenter weatherPresenter);

    void inject(ForecastPresenter forecastPresenter);

    void inject(ForecastFragment forecastFragment);

    void inject(WeatherFragment weatherFragment);
}
