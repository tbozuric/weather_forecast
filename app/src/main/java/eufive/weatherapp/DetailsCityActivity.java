package eufive.weatherapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import eufive.weatherapp.dagger.activity.ActivityComponent;
import eufive.weatherapp.dagger.activity.DaggerActivity;
import eufive.weatherapp.presenter.WeatherDetailsPresenter;
import eufive.weatherapp.viewModel.CityWeatherDetailsViewModel;
import eufive.weatherapp.viewModel.CityWeatherViewModel;

public final class DetailsCityActivity extends DaggerActivity implements DetailsWeatherView {

    private static final String KEY_CITY_WEATHER = "city";

    @BindView(R.id.city_name_et) TextView nameOfCity;
    @BindView(R.id.max_temp_et) TextView maxTemp;
    @BindView(R.id.min_temp_et) TextView minTemp;
    @BindView(R.id.temp_et) TextView temp;
    @BindView(R.id.pressure_et) TextView pressure;
    @BindView(R.id.humidity_et) TextView humidity;
    @BindView(R.id.wind_speed_et) TextView windSpeed;
    @BindView(R.id.description_et) TextView description;
    @BindString(R.string.celsius_temperature_format) String temperatureFormat;
    @BindString(R.string.pressure_format) String pressureFormat;
    @BindString(R.string.humidity_format) String humidityFormat;

    @Inject WeatherDetailsPresenter weatherDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_city);
        ButterKnife.bind(this);
        final Integer id = ((CityWeatherViewModel) getIntent().getParcelableExtra(KEY_CITY_WEATHER)).id;
        weatherDetailsPresenter.setView(this);
        weatherDetailsPresenter.citySelected(id);
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void renderDetailsWeather(final CityWeatherDetailsViewModel cityWeatherDetailsViewModel) {
        nameOfCity.setText(cityWeatherDetailsViewModel.nameOfCity);
        maxTemp.setText(String.format(temperatureFormat, cityWeatherDetailsViewModel.maximumTemperature));
        minTemp.setText(String.format(temperatureFormat, cityWeatherDetailsViewModel.minimumTemperature));
        temp.setText(String.format(temperatureFormat, cityWeatherDetailsViewModel.temperature));
        pressure.setText(String.format(pressureFormat, cityWeatherDetailsViewModel.pressure));
        humidity.setText(String.format(humidityFormat, cityWeatherDetailsViewModel.humidity));
        windSpeed.setText(String.valueOf(cityWeatherDetailsViewModel.windSpeed));
        description.setText(cityWeatherDetailsViewModel.description);
    }

    @Override
    public void showMessage(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        weatherDetailsPresenter.activate();
    }

    @Override
    protected void onPause() {
        weatherDetailsPresenter.deactivate();
        super.onPause();

    }
}
