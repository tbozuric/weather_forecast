package eufive.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import eufive.weatherapp.adapters.WeatherAdapter;
import eufive.weatherapp.dagger.fragment.DaggerFragment;
import eufive.weatherapp.dagger.fragment.FragmentComponent;
import eufive.weatherapp.image.ImageLoader;
import eufive.weatherapp.presenter.WeatherPresenter;
import eufive.weatherapp.viewModel.CityWeatherViewModel;

public final class WeatherFragment extends DaggerFragment implements WeatherView, WeatherAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String KEY_CITY_WEATHER = "city";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_weather) SwipeRefreshLayout swipeRefreshLayout;
    @BindString(R.string.date_format) String dateFormat;

    @Inject WeatherPresenter presenter;
    @Inject ImageLoader imageLoader;
    @Inject LayoutInflater inflater;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private final List<CityWeatherViewModel> cities = new ArrayList<>();

    public WeatherFragment() {}

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        initRecycleView();
        presenter.setWeatherView(this);
        presenter.loadWeatherForCities();
        initRefreshLayout();
        return view;
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecycleView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WeatherAdapter(cities, this, imageLoader, inflater, new SimpleDateFormat(dateFormat));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        presenter.loadWeatherForCities();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(final CityWeatherViewModel cityWeatherViewModel) {
        presenter.itemSelected(cityWeatherViewModel);
    }

    @Override
    public void renderData(final List<CityWeatherViewModel> cityList) {
        cities.clear();
        cities.addAll(cityList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(final String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDetails(final CityWeatherViewModel cityWeatherViewModel) {
        Intent intent = new Intent(getContext(), DetailsCityActivity.class);
        intent.putExtra(KEY_CITY_WEATHER, cityWeatherViewModel);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.activate();
    }

    @Override
    public void onPause() {
        presenter.deactivate();
        super.onPause();

    }
}
