package eufive.weatherapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eufive.weatherapp.ForecastFragment;
import eufive.weatherapp.WeatherFragment;
import eufive.weatherapp.exception.UnimplementedSwitchClauseException;

public final class ViewPagerAdapter extends FragmentPagerAdapter {

    private final static int NUMBER_OF_ITEMS = 2;
    private static final String WEATHER_FRAGMENT_NAME = "Weather";
    private static final int POSITION_OF_WEATHER_FRAGMENT = 0;
    private static final String FORECAST_FRAGMENT_NAME = "Forecast";
    private static final int POSITION_OF_FORECAST_FRAGMENT = 1;

    public ViewPagerAdapter(final FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case POSITION_OF_WEATHER_FRAGMENT:
                return WeatherFragment.newInstance();
            case POSITION_OF_FORECAST_FRAGMENT:
                return ForecastFragment.newInstance();
            default:
                throw new UnimplementedSwitchClauseException("Unexpected position in getItem method. Position : " + position);
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        switch (position) {
            case POSITION_OF_WEATHER_FRAGMENT:
                return WEATHER_FRAGMENT_NAME;
            case POSITION_OF_FORECAST_FRAGMENT:
                return FORECAST_FRAGMENT_NAME;
            default:
                throw new UnimplementedSwitchClauseException("Unexpected position in getPageTitle method. Position : " + position);
        }
    }
}
