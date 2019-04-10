package eufive.weatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eufive.weatherapp.adapters.ForecastAdapter;
import eufive.weatherapp.dagger.fragment.DaggerFragment;
import eufive.weatherapp.dagger.fragment.FragmentComponent;
import eufive.weatherapp.image.ImageLoader;
import eufive.weatherapp.presenter.ForecastPresenter;
import eufive.weatherapp.viewModel.ForecastViewModel;

public final class ForecastFragment extends DaggerFragment implements ForecastView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.forecast_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.forecast_fragment) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.forecast_city) EditText nameOfCity;

    @Inject ForecastPresenter presenter;
    @Inject ImageLoader imageLoader;
    @Inject LayoutInflater inflater;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private final List<ForecastViewModel> forecasts = new ArrayList<>();

    public ForecastFragment() {}

    public static ForecastFragment newInstance() {
        return new ForecastFragment();
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
        final View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        initRecycleView();
        initRefreshLayout();
        return view;
    }

    @Override
    public void onRefresh() {
        final String city = nameOfCity.getText().toString();
        presenter.loadForecastForCity(city);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecycleView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ForecastAdapter(forecasts, imageLoader, inflater);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void renderData(final List<ForecastViewModel> forecastViewModels) {
        forecasts.clear();
        forecasts.addAll(forecastViewModels);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(final String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.search_button)
    public void onClick() {
        final String city = nameOfCity.getText().toString();
        presenter.loadForecastForCity(city);
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
