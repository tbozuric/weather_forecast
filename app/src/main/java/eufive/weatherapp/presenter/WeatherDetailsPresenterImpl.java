package eufive.weatherapp.presenter;

import android.content.res.Resources;

import java.lang.ref.WeakReference;

import eufive.weatherapp.DetailsWeatherView;
import eufive.weatherapp.R;
import eufive.weatherapp.mapper.CityToViewModelMapper;
import eufive.weatherapp.repository.WeatherRepository;
import eufive.weatherapp.viewModel.CityWeatherDetailsViewModel;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public final class WeatherDetailsPresenterImpl extends BasePresenterImpl implements WeatherDetailsPresenter {

    private WeakReference<DetailsWeatherView> view;
    private final WeatherRepository weatherRepository;
    private final CityToViewModelMapper cityToViewModelMapper;
    private final Scheduler mainScheduler;
    private final Scheduler backgroundScheduler;
    private String errorMessage;

    //TODO: BASE PRESENTER TO PREVENT detailsWeatherView.get()!=null checks;

    public WeatherDetailsPresenterImpl(final WeatherRepository weatherRepository,
                                       final CityToViewModelMapper cityToViewModelMapper, final Scheduler mainScheduler, final Scheduler backgroundScheduler,final Resources resource) {
        this.weatherRepository = weatherRepository;
        this.cityToViewModelMapper = cityToViewModelMapper;
        this.mainScheduler = mainScheduler;
        this.backgroundScheduler = backgroundScheduler;
        errorMessage = resource.getString(R.string.weather_error_message);

    }

    @Override
    public void citySelected(final int cityId) {
        addSubscription(Single.concat(weatherRepository.getCityWeatherDetailsData(cityId)
                                                       .map(cityToViewModelMapper::getCityWeatherDetailsViewModel)
                                                       .onErrorResumeNext(throwable -> Single.just(CityWeatherDetailsViewModel.EMPTY)),
                                      weatherRepository.getCityWeatherDetailsDataOffline(cityId)
                                                       .map(cityToViewModelMapper::getCityWeatherDetailsViewModelFromDatabaseModel))
                              .filter(cityWeatherDetailsViewModel -> cityWeatherDetailsViewModel != CityWeatherDetailsViewModel.EMPTY)
                              .firstOrError()
                              .subscribeOn(backgroundScheduler)
                              .observeOn(mainScheduler)
                              .subscribe(this::onFetchWeatherDetailsForCitySuccess, this::onFetchWeatherDetailsForCityError));
    }

    private void onFetchWeatherDetailsForCityError(final Throwable throwable) {
        final DetailsWeatherView detailsWeatherView = view.get();
        if (detailsWeatherView != null) {
            detailsWeatherView.showMessage(errorMessage);
        }
    }

    private void onFetchWeatherDetailsForCitySuccess(final CityWeatherDetailsViewModel cityWeatherDetailsViewModel) {
        final DetailsWeatherView detailsWeatherView = view.get();
        if (detailsWeatherView != null) {
            detailsWeatherView.renderDetailsWeather(cityWeatherDetailsViewModel);
        }
    }

    @Override
    public void setView(final DetailsWeatherView detailsWeatherView) {
        this.view = new WeakReference<DetailsWeatherView>(detailsWeatherView);
    }
}
