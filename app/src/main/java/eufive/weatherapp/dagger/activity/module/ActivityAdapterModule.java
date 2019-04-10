package eufive.weatherapp.dagger.activity.module;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.adapters.ViewPagerAdapter;
import eufive.weatherapp.dagger.activity.ActivityScope;

@Module
public final class ActivityAdapterModule {

    @ActivityScope
    @Provides
    ViewPagerAdapter provideViewPagerAdapter(FragmentManager fragmentManager) {
        return new ViewPagerAdapter(fragmentManager);
    }

    public interface Expose {

        ViewPagerAdapter viewPagerAdapter();
    }
}
