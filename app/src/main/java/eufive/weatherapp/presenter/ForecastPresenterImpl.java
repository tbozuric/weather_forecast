package eufive.weatherapp.presenter;

import android.content.res.Resources;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import eufive.weatherapp.ForecastView;
import eufive.weatherapp.R;
import eufive.weatherapp.mapper.CityToViewModelMapper;
import eufive.weatherapp.model.CityForecastList;
import eufive.weatherapp.repository.ForecastRepository;
import eufive.weatherapp.viewModel.ForecastViewModel;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;

public class ForecastPresenterImpl extends BasePresenterImpl implements ForecastPresenter {

    private WeakReference<ForecastView> forecastView;
    private final ForecastRepository forecastRepository;
    private final CityToViewModelMapper cityToViewModelMapper;
    private final Scheduler mainScheduler;
    private final Scheduler backgroundSceScheduler;
    private String fetchForecastErrorMessage;
    private String loadForecastMessage;
    private String fetchForecastSuccessMessageFormat;

    public ForecastPresenterImpl(final ForecastRepository repository, final CityToViewModelMapper cityToViewModelMapper, final Scheduler mainScheduler,
                                 final Scheduler backgroundSceScheduler, final Resources resources) {
        this.forecastRepository = repository;
        this.cityToViewModelMapper = cityToViewModelMapper;
        this.mainScheduler = mainScheduler;
        this.backgroundSceScheduler = backgroundSceScheduler;
        fetchForecastErrorMessage = resources.getString(R.string.fetch_forecast_error_message);
        loadForecastMessage = resources.getString(R.string.load_forecast_message);
        fetchForecastSuccessMessageFormat = resources.getString(R.string.fetch_forecast_success_message);
    }

    @Override
    public void loadForecastForCity(final String city) {
        if (city.isEmpty()) {
            forecastView.get().showMessage(loadForecastMessage);
        } else {
            addSubscription(forecastRepository.getForecastDataForCity(city)
                                              .subscribeOn(backgroundSceScheduler)
                                              .observeOn(mainScheduler)
                                              .subscribe(this::onFetchForecastForCitySuccess, this::onFetchForecastForCityError));
        }
    }

    @Override
    public void setView(final ForecastView forecastView) {
        this.forecastView = new WeakReference<>(forecastView);
    }

    private void onFetchForecastForCityError(final Throwable throwable) {
        doIfViewNotNull(forecastView -> forecastView.showMessage(fetchForecastErrorMessage));
    }

    private void onFetchForecastForCitySuccess(CityForecastList cityForecastList) {
        List<ForecastViewModel> forecastViewModels = new ArrayList<>(
                cityToViewModelMapper.getForecastViewModels(cityForecastList.getCityForecast(), cityForecastList.getCityInfo().getName()));
        doIfViewNotNull(forecastView -> {
            forecastView.renderData(forecastViewModels);
            forecastView.showMessage(String.format(fetchForecastSuccessMessageFormat, forecastViewModels.get(0).nameOfCity));
        });
    }

    //TODO: BASE PRESENTER TO PREVENT forecastView.get()!=null checks;
    private void doIfViewNotNull(Consumer<ForecastView> consumer) {
        try {
            final ForecastView view = forecastView.get();
            if (view != null) {
                consumer.accept(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
