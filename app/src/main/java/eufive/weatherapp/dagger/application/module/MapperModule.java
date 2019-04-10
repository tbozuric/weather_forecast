package eufive.weatherapp.dagger.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.mapper.CityToViewModelMapper;

@Module
public final class MapperModule {

    @Singleton
    @Provides
    CityToViewModelMapper provideCityToViewModelMapper() {
        return new CityToViewModelMapper();
    }

    public interface Exposes {

        CityToViewModelMapper cityToViewModelMapper();
    }
}
