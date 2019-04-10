package eufive.weatherapp.dagger.application;

import eufive.weatherapp.application.WeatherApplication;

public interface ApplicationComponentInjects {

    void inject(WeatherApplication weatherApplication);
}
