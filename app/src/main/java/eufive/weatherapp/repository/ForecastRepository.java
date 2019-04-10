package eufive.weatherapp.repository;

import eufive.weatherapp.model.CityForecastList;
import io.reactivex.Single;

public interface ForecastRepository {

    Single<CityForecastList> getForecastDataForCity(final String city);
}
