package eufive.weatherapp.dagger.activity.module;

import android.content.res.Resources;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.dagger.activity.ActivityScope;
import eufive.weatherapp.dagger.activity.DaggerActivity;
import eufive.weatherapp.dagger.application.module.ThreadingModule;
import eufive.weatherapp.mapper.CityToViewModelMapper;
import eufive.weatherapp.presenter.WeatherDetailsPresenter;
import eufive.weatherapp.presenter.WeatherDetailsPresenterImpl;
import eufive.weatherapp.repository.WeatherRepository;
import io.reactivex.Scheduler;

@Module
public final class ActivityPresenterModule {

    private final DaggerActivity daggerActivity;

    public ActivityPresenterModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    @ActivityScope
    WeatherDetailsPresenter provideWeatherDetailsPresenter(final WeatherRepository weatherRepository, final CityToViewModelMapper cityToViewModelMapper,
                                                           @Named(ThreadingModule.BACKGROUND_SCHEDULER) final Scheduler backgroundScheduler,
                                                           @Named(ThreadingModule.MAIN_SCHEDULER) final Scheduler mainScheduler,final  Resources resources) {
        WeatherDetailsPresenter weatherDetailsPresenter = new WeatherDetailsPresenterImpl(weatherRepository, cityToViewModelMapper, mainScheduler, backgroundScheduler, resources);
        daggerActivity.getActivityComponent().inject(weatherDetailsPresenter);
        return weatherDetailsPresenter;
    }

    public interface Exposes {

        WeatherDetailsPresenter weatherDetailsPresenter();
    }
}
