package eufive.weatherapp.dagger.activity.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.dagger.activity.ActivityScope;
import eufive.weatherapp.dagger.activity.DaggerActivity;
import eufive.weatherapp.dagger.activity.ForActivity;

@Module
public final class ActivityModule {

    private final DaggerActivity daggerActivity;

    public ActivityModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    Context provideActivityContext() {
        return daggerActivity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return daggerActivity;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManger() {
        return daggerActivity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater(@ForActivity final Context context) {
        return LayoutInflater.from(context);
    }

    public interface Exposes {

        Activity activity();

        @ForActivity
        Context context();

        FragmentManager fragmentManager();

        LayoutInflater layoutInflater();
    }
}
