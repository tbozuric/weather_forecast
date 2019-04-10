package eufive.weatherapp;

import java.util.List;

import eufive.weatherapp.viewModel.ForecastViewModel;

public interface ForecastView {

    void renderData(List<ForecastViewModel> forecastViewModels);

    void showMessage(String message);
}
