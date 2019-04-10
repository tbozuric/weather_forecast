package eufive.weatherapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import eufive.weatherapp.R;
import eufive.weatherapp.image.ImageLoader;
import eufive.weatherapp.viewModel.ForecastViewModel;

public final class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private final ImageLoader imageLoader;
    private final List<ForecastViewModel> forecastViewModels;
    private final LayoutInflater inflater;

    public ForecastAdapter(final List<ForecastViewModel> forecastViewModels, final ImageLoader imageLoader, final LayoutInflater inflater) {
        this.imageLoader = imageLoader;
        this.forecastViewModels = forecastViewModels;
        this.inflater = inflater;
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View contactView = inflater.inflate(R.layout.forecast_card_model, parent, false);
        return new ForecastAdapter.ViewHolder(contactView, imageLoader);
    }

    @Override
    public void onBindViewHolder(final ForecastAdapter.ViewHolder holder, final int position) {
        final ForecastViewModel forecastViewModel = forecastViewModels.get(position);
        holder.bind(forecastViewModel);
    }

    @Override
    public int getItemCount() {
        return forecastViewModels.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.forecast_date_value) TextView date;
        @BindView(R.id.forecast_min_temperature_value) TextView minTemp;
        @BindView(R.id.forecast_max_temperature_value) TextView maxTemp;
        @BindView(R.id.forecast_weather_icon) ImageView weatherIcon;
        @BindView(R.id.forecast_pressure_value) TextView pressure;
        @BindView(R.id.forecast_humidity_value) TextView humidity;
        @BindString(R.string.celsius_temperature_format) String temperatureFormat;
        @BindString(R.string.pressure_format) String pressureFormat;
        @BindString(R.string.humidity_format) String humidityFormat;

        private final ImageLoader imageLoader;

        public ViewHolder(final View itemView, final ImageLoader imageLoader) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.imageLoader = imageLoader;
        }

        private void bind(final ForecastViewModel forecastViewModel) {
            date.setText(forecastViewModel.date);
            minTemp.setText(String.format(temperatureFormat, forecastViewModel.minimumTemperature));
            maxTemp.setText(String.format(temperatureFormat, forecastViewModel.maximumTemperature));
            pressure.setText(String.format(pressureFormat, forecastViewModel.pressure));
            humidity.setText(String.format(humidityFormat, forecastViewModel.humidity));
            imageLoader.loadImage(forecastViewModel.icon, weatherIcon);
        }
    }
}

