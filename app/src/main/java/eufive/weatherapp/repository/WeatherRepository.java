package eufive.weatherapp.repository;

import java.util.List;

import eufive.weatherapp.database.CityWeatherDbModel;
import eufive.weatherapp.model.CitiesList;
import eufive.weatherapp.model.City;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface WeatherRepository {

    Single<CitiesList> getCitiesWeatherData();

    Single<City> getCityWeatherDetailsData(int id);

    Single<List<CityWeatherDbModel>> getAllDataOffline();

    Completable insertDataOffline(List<CityWeatherDbModel> cityWeatherDbModels);

    Single<CityWeatherDbModel> getCityWeatherDetailsDataOffline(int id);
}
