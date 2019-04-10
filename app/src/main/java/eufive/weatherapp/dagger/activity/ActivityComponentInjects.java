package eufive.weatherapp.dagger.activity;

import eufive.weatherapp.DetailsCityActivity;
import eufive.weatherapp.MainActivity;
import eufive.weatherapp.presenter.WeatherDetailsPresenter;

public interface ActivityComponentInjects {

    void inject(DetailsCityActivity detailsCityActivity);

    void inject(WeatherDetailsPresenter weatherDetailsPresenter);

    void inject(MainActivity mainActivity);
}

