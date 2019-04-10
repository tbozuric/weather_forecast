package eufive.weatherapp.dagger.application.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.client.ApiService;
import eufive.weatherapp.dagger.application.ForApplication;
import eufive.weatherapp.database.AppDatabase;
import eufive.weatherapp.database.CityWeatherDao;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class DataModule {

    private static final String ROOT_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String DB_NAME = "weatherRecords.db";

    @Singleton
    @Provides
    ApiService provideApiService() {
        return new Retrofit
                .Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    @Singleton
    @Provides
    CityWeatherDao provideCityWeatherDao(@ForApplication final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build().getCityWeatherDao();
    }

    public interface Exposes {

        ApiService apiService();

        CityWeatherDao provideCityWeatherDao();
    }
}
