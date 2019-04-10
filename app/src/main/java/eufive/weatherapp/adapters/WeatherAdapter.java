package eufive.weatherapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eufive.weatherapp.R;
import eufive.weatherapp.image.ImageLoader;
import eufive.weatherapp.viewModel.CityWeatherViewModel;

public final class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    public interface OnItemClickListener {

        void onItemClick(CityWeatherViewModel cityWeatherViewModel);
    }

    private final List<CityWeatherViewModel> cities;

    private final OnItemClickListener listener;

    private final ImageLoader imageLoader;

    private final LayoutInflater inflater;

    private final SimpleDateFormat dateTimeFormat;

    public WeatherAdapter(final List<CityWeatherViewModel> cities, final OnItemClickListener listener, final ImageLoader imageLoader, final LayoutInflater inflater,
                          final SimpleDateFormat dateTimeFormat) {
        this.cities = cities;
        this.listener = listener;
        this.imageLoader = imageLoader;
        this.inflater = inflater;
        this.dateTimeFormat = dateTimeFormat;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View contactView = inflater.inflate(R.layout.activity_main_card_model, parent, false);
        return new ViewHolder(contactView, imageLoader, listener, dateTimeFormat);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CityWeatherViewModel cityWeatherViewModel = cities.get(position);
        holder.bind(cityWeatherViewModel);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_of_city_value) TextView nameOfCity;
        @BindView(R.id.min_temp_value) TextView minTemp;
        @BindView(R.id.max_temp_value) TextView maxTemp;
        @BindView(R.id.image_view) ImageView weatherIcon;
        @BindView(R.id.last_updated_value) TextView lastUpdated;
        @BindString(R.string.celsius_temperature_format) String temperatureFormat;

        private final ImageLoader imageLoader;
        private final OnItemClickListener listener;
        private final SimpleDateFormat dateTimeFormat;

        private CityWeatherViewModel cityWeatherViewModel;

        public ViewHolder(final View itemView, final ImageLoader imageLoader, final OnItemClickListener listener, final SimpleDateFormat dateTimeFormat) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.imageLoader = imageLoader;
            this.listener = listener;
            this.dateTimeFormat = dateTimeFormat;
        }

        private void bind(final CityWeatherViewModel cityWeatherViewModel) {
            this.cityWeatherViewModel = cityWeatherViewModel;
            nameOfCity.setText(cityWeatherViewModel.nameOfCity.toUpperCase());
            minTemp.setText(String.format(temperatureFormat, cityWeatherViewModel.minimumTemperature));
            maxTemp.setText(String.format(temperatureFormat, cityWeatherViewModel.maximumTemperature));
            lastUpdated.setText(dateFormatToString(cityWeatherViewModel.lastUpdated));
            imageLoader.loadImage(cityWeatherViewModel.icon, weatherIcon);
        }

        private String dateFormatToString(final Date date) {
            return dateTimeFormat.format(date);
        }

        @OnClick(R.id.card_view)
        public void onClick() {
            listener.onItemClick(cityWeatherViewModel);
        }
    }
}

