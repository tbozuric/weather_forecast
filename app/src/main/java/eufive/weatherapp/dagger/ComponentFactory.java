package eufive.weatherapp.dagger;

import eufive.weatherapp.application.WeatherApplication;
import eufive.weatherapp.dagger.activity.ActivityComponent;
import eufive.weatherapp.dagger.activity.DaggerActivity;
import eufive.weatherapp.dagger.application.ApplicationComponent;
import eufive.weatherapp.dagger.fragment.DaggerFragment;
import eufive.weatherapp.dagger.fragment.FragmentComponent;

public final class ComponentFactory {

    private ComponentFactory() {

    }

    public static ApplicationComponent createApplicationComponent(final WeatherApplication weatherApplication) {
        return ApplicationComponent.Initializer.init(weatherApplication);
    }

    public static ActivityComponent createActivityComponent(final DaggerActivity daggerActivity, final WeatherApplication weatherApplication) {
        return ActivityComponent.Initializer.init(daggerActivity, weatherApplication.getApplicationComponent());
    }

    public static FragmentComponent createFragmentComponent(final DaggerFragment daggerFragment, final ActivityComponent activityComponent) {
        return FragmentComponent.Initializer.init(daggerFragment, activityComponent);
    }
}
