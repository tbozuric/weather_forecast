package eufive.weatherapp.dagger.fragment.module;

import android.content.res.Resources;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.dagger.application.module.ThreadingModule;
import eufive.weatherapp.dagger.fragment.DaggerFragment;
import eufive.weatherapp.dagger.fragment.FragmentComponent;
import eufive.weatherapp.dagger.fragment.FragmentScope;
import eufive.weatherapp.mapper.CityToViewModelMapper;
import eufive.weatherapp.presenter.ForecastPresenter;
import eufive.weatherapp.presenter.ForecastPresenterImpl;
import eufive.weatherapp.presenter.WeatherPresenter;
import eufive.weatherapp.presenter.WeatherPresenterImpl;
import eufive.weatherapp.repository.ForecastRepository;
import eufive.weatherapp.repository.WeatherRepository;
import io.reactivex.Scheduler;

@Module
public final class FragmentPresenterModule {

    private final DaggerFragment daggerFragment;

    public FragmentPresenterModule(final DaggerFragment daggerFragment) {
        this.daggerFragment = daggerFragment;
    }

    private FragmentComponent getFragmentComponent() {
        return daggerFragment.getFragmentComponent();
    }

    @Provides
    @FragmentScope
    ForecastPresenter provideForecastPresenter(final ForecastRepository forecastRepository, final CityToViewModelMapper cityToViewModelMapper,
                                               @Named(ThreadingModule.BACKGROUND_SCHEDULER) final Scheduler backgroundScheduler,
                                               @Named(ThreadingModule.MAIN_SCHEDULER) final Scheduler mainScheduler, final Resources resources) {
        final ForecastPresenter forecastPresenter = new ForecastPresenterImpl(forecastRepository, cityToViewModelMapper, mainScheduler, backgroundScheduler, resources);
        getFragmentComponent().inject(forecastPresenter);
        return forecastPresenter;
    }

    @Provides
    @FragmentScope
    WeatherPresenter provideWeatherPresenter(final WeatherRepository weatherRepository, final CityToViewModelMapper cityToViewModelMapper,
                                             @Named(ThreadingModule.BACKGROUND_SCHEDULER) final Scheduler backgroundScheduler,
                                             @Named(ThreadingModule.MAIN_SCHEDULER) final Scheduler mainScheduler, final  Resources resources) {
        final WeatherPresenter weatherPresenter = new WeatherPresenterImpl(weatherRepository, cityToViewModelMapper, mainScheduler, backgroundScheduler, resources);
        getFragmentComponent().inject(weatherPresenter);
        return weatherPresenter;
    }
}
