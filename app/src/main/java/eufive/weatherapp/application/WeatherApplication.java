package eufive.weatherapp.application;

import android.app.Application;

import eufive.weatherapp.dagger.ComponentFactory;
import eufive.weatherapp.dagger.application.ApplicationComponent;

public final class WeatherApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        injectMe();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void injectMe() {
        applicationComponent.inject(this);
    }

    private void initApplicationComponent() {
        applicationComponent = ComponentFactory.createApplicationComponent(this);
    }
}


