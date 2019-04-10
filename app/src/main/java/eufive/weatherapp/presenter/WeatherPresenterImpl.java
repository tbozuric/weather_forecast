package eufive.weatherapp.presenter;

import android.content.res.Resources;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import eufive.weatherapp.R;
import eufive.weatherapp.WeatherView;
import eufive.weatherapp.mapper.CityToViewModelMapper;
import eufive.weatherapp.model.City;
import eufive.weatherapp.repository.WeatherRepository;
import eufive.weatherapp.viewModel.CityWeatherViewModel;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public final class WeatherPresenterImpl extends BasePresenterImpl implements WeatherPresenter {

    private WeakReference<WeatherView> weatherView;
    private final WeatherRepository weatherRepository;
    private final CityToViewModelMapper cityToViewModelMapper;
    private final Scheduler mainScheduler;
    private final Scheduler backgroundSceScheduler;
    private  String errorMessage;

    //TODO: BASE PRESENTER TO PREVENT weatherView.get()!=null checks;

    public WeatherPresenterImpl(final WeatherRepository weatherRepository, final CityToViewModelMapper cityToViewModelMapper, final Scheduler mainScheduler,
                                final Scheduler bakgroundScheduler, Resources  resources) {

        this.weatherRepository = weatherRepository;
        this.cityToViewModelMapper = cityToViewModelMapper;
        this.mainScheduler = mainScheduler;
        this.backgroundSceScheduler = bakgroundScheduler;
        errorMessage = resources.getString(R.string.weather_error_message);
    }

    @Override
    public void itemSelected(final CityWeatherViewModel cityWeatherViewModel) {
        final WeatherView view = weatherView.get();
        if (view != null) {
            weatherView.get().navigateToDetails(cityWeatherViewModel);
        }
    }

    @Override
    public void loadWeatherForCities() {
        addSubscription(Single.concat(weatherRepository.getCitiesWeatherData()
                                                       .flatMap(citiesList -> updateDataInDatabase(citiesList.getCities()).toSingleDefault(citiesList))
                                                       .map(citiesList -> cityToViewModelMapper.getCityWeatherViewModels(citiesList.getCities()))
                                                       .onErrorResumeNext(throwable -> Single.just(new ArrayList<>())),
                                      weatherRepository.getAllDataOffline()
                                                       .map(cityToViewModelMapper::getCityWeatherViewModelsFromDatabaseModels))
                              .filter(cityWeatherViewModelList -> !cityWeatherViewModelList.isEmpty())
                              .firstOrError()
                              .subscribeOn(backgroundSceScheduler)
                              .observeOn(mainScheduler)
                              .subscribe(this::onFetchWeatherForCitySuccess, this::onFetchWeatherForCityError));
    }

    private void onFetchWeatherForCityError(final Throwable throwable) {
        final WeatherView weatherView = this.weatherView.get();
        if (weatherView != null) {
            weatherView.showMessage(errorMessage);
        }
    }

    private void onFetchWeatherForCitySuccess(final List<CityWeatherViewModel> cityWeatherViewModels) {
        final WeatherView weatherView = this.weatherView.get();
        if (weatherView != null) {
            weatherView.renderData(cityWeatherViewModels);
        }
    }

    private Completable updateDataInDatabase(final ArrayList<City> cities) {
        return weatherRepository.insertDataOffline(cityToViewModelMapper.getCityWeatherDbViewModels(cities));
    }

    public void setWeatherView(final WeatherView weatherView) {
        this.weatherView = new WeakReference<WeatherView>(weatherView);
    }
}
