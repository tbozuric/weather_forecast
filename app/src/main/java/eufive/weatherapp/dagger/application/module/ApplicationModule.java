package eufive.weatherapp.dagger.application.module;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.application.WeatherApplication;
import eufive.weatherapp.dagger.application.ForApplication;

@Module
public final class ApplicationModule {

    private final WeatherApplication weatherApplication;

    public ApplicationModule(@NonNull final WeatherApplication weatherApplication) {
        this.weatherApplication = weatherApplication;
    }

    @Singleton
    @Provides
    WeatherApplication provideWeatherApplication() {
        return weatherApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return weatherApplication;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return weatherApplication.getResources();
    }

    public interface Exposes {

        WeatherApplication weatherApplication();

        @ForApplication
        Context context();

        Resources resources();
    }
}
