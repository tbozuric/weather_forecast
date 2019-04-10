package eufive.weatherapp.client;

import eufive.weatherapp.model.CitiesList;
import eufive.weatherapp.model.City;
import eufive.weatherapp.model.CityForecastList;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Single<City> getCityWithWeatherDetails(@Query("id") String id, @Query("appid") String appId);

    @GET("group")
    Single<CitiesList> getCitiesWithWeatherDetails(@Query("id") String id, @Query("appid") String appId);

    @GET("forecast")
    Single<CityForecastList> getForecastForCity(@Query("q") String city, @Query("appid") String appId);
}
