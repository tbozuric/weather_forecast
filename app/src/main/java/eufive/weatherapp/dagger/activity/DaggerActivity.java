package eufive.weatherapp.dagger.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import eufive.weatherapp.application.WeatherApplication;
import eufive.weatherapp.dagger.ComponentFactory;

public abstract class DaggerActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getActivityComponent());
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, getWeatherApplication());
        }

        return activityComponent;
    }

    private WeatherApplication getWeatherApplication() {
        return ((WeatherApplication) getApplication());
    }

    protected abstract void inject(final ActivityComponent activityComponent);
}

