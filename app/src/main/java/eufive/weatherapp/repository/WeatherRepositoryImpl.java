package eufive.weatherapp.repository;

import java.util.List;

import eufive.weatherapp.client.ApiService;
import eufive.weatherapp.database.CityWeatherDao;
import eufive.weatherapp.database.CityWeatherDbModel;
import eufive.weatherapp.model.CitiesList;
import eufive.weatherapp.model.City;
import io.reactivex.Completable;
import io.reactivex.Single;

public final class WeatherRepositoryImpl implements WeatherRepository {

    private static final String APPID = "332b18d822416080fcfc587f504a6a49";
    private static final Integer ZG_ID = 3186886;
    private static final Integer ST_ID = 3190261;
    private static final Integer RI_ID = 3191648;
    private static final Integer OS_ID = 3193935;
    private static final String ids = ZG_ID + "," + ST_ID + "," + RI_ID + "," + OS_ID;

    private final ApiService apiService;
    private final CityWeatherDao cityWeatherDao;

    public WeatherRepositoryImpl(ApiService apiService, final CityWeatherDao cityWeatherDao) {
        this.apiService = apiService;
        this.cityWeatherDao = cityWeatherDao;
    }

    @Override
    public Single<CitiesList> getCitiesWeatherData() {
        return apiService.getCitiesWithWeatherDetails(ids, APPID);
    }

    @Override
    public Single<City> getCityWeatherDetailsData(final int id) {
        return apiService.getCityWithWeatherDetails(String.valueOf(id), APPID);
    }

    @Override
    public Single<List<CityWeatherDbModel>> getAllDataOffline() {
        return cityWeatherDao.getAllData();
    }

    @Override
    public Completable insertDataOffline(final List<CityWeatherDbModel> cityWeatherDbModels) {
        return Completable.fromAction(() -> {
            cityWeatherDao.insertMultipleData(cityWeatherDbModels.toArray(new CityWeatherDbModel[0]));
        });
    }

    @Override
    public Single<CityWeatherDbModel> getCityWeatherDetailsDataOffline(final int id) {
        return cityWeatherDao.getItemById(id);
    }
}