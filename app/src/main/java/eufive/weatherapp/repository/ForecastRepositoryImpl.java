package eufive.weatherapp.repository;

import eufive.weatherapp.client.ApiService;
import eufive.weatherapp.model.CityForecastList;
import io.reactivex.Single;

public class ForecastRepositoryImpl implements ForecastRepository {

    private final ApiService apiService;
    private static final String APPID = "332b18d822416080fcfc587f504a6a49";

    public ForecastRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<CityForecastList> getForecastDataForCity(final String city) {
        return apiService.getForecastForCity(city, APPID);
    }
}
