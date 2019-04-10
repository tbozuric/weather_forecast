package eufive.weatherapp.dagger.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.client.ApiService;
import eufive.weatherapp.database.CityWeatherDao;
import eufive.weatherapp.repository.ForecastRepository;
import eufive.weatherapp.repository.ForecastRepositoryImpl;
import eufive.weatherapp.repository.WeatherRepository;
import eufive.weatherapp.repository.WeatherRepositoryImpl;

@Module
public final class DomainModule {

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(final ApiService apiService, final CityWeatherDao cityWeatherDao) {
        return new WeatherRepositoryImpl(apiService, cityWeatherDao);
    }

    @Provides
    @Singleton
    ForecastRepository provideForecastRespository(final ApiService apiService) {
        return new ForecastRepositoryImpl(apiService);
    }

    public interface Exposes {

        WeatherRepository weatherRepository();

        ForecastRepository forecastRepository();
    }
}
