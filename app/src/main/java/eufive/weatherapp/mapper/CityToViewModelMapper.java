package eufive.weatherapp.mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eufive.weatherapp.database.CityWeatherDbModel;
import eufive.weatherapp.model.City;
import eufive.weatherapp.model.CityForecast;
import eufive.weatherapp.viewModel.CityWeatherDetailsViewModel;
import eufive.weatherapp.viewModel.CityWeatherViewModel;
import eufive.weatherapp.viewModel.ForecastViewModel;

public final class CityToViewModelMapper {

    private static final String BASE_URL = "http://openweathermap.org/img/w/";
    private static final String FORMAT = ".png";

    public CityWeatherDetailsViewModel getCityWeatherDetailsViewModel(final City city) {
        return new CityWeatherDetailsViewModel(city.getId(), city.getName(), city.getWeatherDetails().getHumidity(), city.getWeatherDetails().getPressure(),
                                               city.getWeather().get(0).getDescription(),
                                               city.getWind().getSpeed(), city.getWeatherDetails().getTempMax(), city.getWeatherDetails().getTempMin(),
                                               city.getWeatherDetails().getTemp());
    }

    public List<CityWeatherDbModel> getCityWeatherDbViewModels(final List<City> cities) {
        final List<CityWeatherDbModel> cityWeatherDbModels = new ArrayList<>(cities.size());
        for (final City city : cities) {
            cityWeatherDbModels.add(getCityWeatherDbModel(city));
        }
        return cityWeatherDbModels;
    }

    private CityWeatherDbModel getCityWeatherDbModel(City city) {
        return new CityWeatherDbModel(city.getId(), city.getWeather().get(0).getDescription(), city.getWeatherDetails().getHumidity(),
                                      city.getWeatherDetails().getTempMax(), city.getWeatherDetails().getTempMin(),
                                      city.getWeatherDetails().getTemp(),
                                      city.getWeatherDetails().getPressure(), city.getName(), city.getWind().getSpeed(),
                                      Calendar.getInstance().getTime(), createUrl(city.getWeather().get(0).getIcon()));
    }

    public List<CityWeatherViewModel> getCityWeatherViewModels(final List<City> cities) {
        final List<CityWeatherViewModel> cityWeatherViewModels = new ArrayList<>(cities.size());
        for (final City city : cities) {
            cityWeatherViewModels.add(
                    new CityWeatherViewModel(city.getId(), city.getName(), city.getWeatherDetails().getTempMin(), city.getWeatherDetails().getTempMax(),
                                             createUrl(city.getWeather().get(0).getIcon()), Calendar.getInstance().getTime()));
        }
        return cityWeatherViewModels;
    }

    public List<CityWeatherViewModel> getCityWeatherViewModelsFromDatabaseModels(final List<CityWeatherDbModel> cityWeatherDbModels) {
        final List<CityWeatherViewModel> cityWeatherViewModels = new ArrayList<>(cityWeatherDbModels.size());

        for (final CityWeatherDbModel cityWeatherDbModel : cityWeatherDbModels) {
            cityWeatherViewModels.add(new CityWeatherViewModel(cityWeatherDbModel.getId(), cityWeatherDbModel.getNameOfCity(), cityWeatherDbModel.getMinimumTemperature(),
                                                               cityWeatherDbModel.getMaximumTemperature(), createUrl(cityWeatherDbModel.getImage()),
                                                               cityWeatherDbModel.getLastUpdated()));
        }
        return cityWeatherViewModels;
    }

    public CityWeatherDetailsViewModel getCityWeatherDetailsViewModelFromDatabaseModel(final CityWeatherDbModel cityWeatherDbModel) {
        return new CityWeatherDetailsViewModel(cityWeatherDbModel.getId(), cityWeatherDbModel.getNameOfCity(), cityWeatherDbModel.getHumidity(), cityWeatherDbModel.getPressure(),
                                               cityWeatherDbModel.getDescription(),
                                               cityWeatherDbModel.getWindSpeed(), cityWeatherDbModel.getMaximumTemperature(), cityWeatherDbModel.getMinimumTemperature(),
                                               cityWeatherDbModel.getTemperature());
    }

    public List<ForecastViewModel> getForecastViewModels(final List<CityForecast> cityForecasts, final String nameOfCity) {
        final List<ForecastViewModel> forecastViewModels = new ArrayList<>(cityForecasts.size());
        for (final CityForecast cityForecast : cityForecasts) {
            forecastViewModels.add(
                    new ForecastViewModel(nameOfCity, cityForecast.getDate(), createUrl(cityForecast.getWeather().get(0).getIcon()), cityForecast.getWeatherDetails().getTempMin(),
                                          cityForecast.getWeatherDetails().getTempMax(), cityForecast.getWeatherDetails().getPressure(),
                                          cityForecast.getWeatherDetails().getHumidity()));
        }
        return forecastViewModels;
    }

    private String createUrl(final String icon) {
        return BASE_URL + icon + FORMAT;
    }
}
