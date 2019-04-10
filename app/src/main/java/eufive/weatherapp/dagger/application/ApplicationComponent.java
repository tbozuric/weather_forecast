package eufive.weatherapp.dagger.application;

import javax.inject.Singleton;

import dagger.Component;
import eufive.weatherapp.application.WeatherApplication;
import eufive.weatherapp.dagger.application.module.ApplicationModule;
import eufive.weatherapp.dagger.application.module.DataModule;
import eufive.weatherapp.dagger.application.module.DomainModule;
import eufive.weatherapp.dagger.application.module.MapperModule;
import eufive.weatherapp.dagger.application.module.ThreadingModule;
import eufive.weatherapp.dagger.application.module.UtilModule;

@Singleton
@Component(modules = {ApplicationModule.class,
                      DataModule.class,
                      DomainModule.class,
                      MapperModule.class,
                      UtilModule.class,
                      ThreadingModule.class})
public interface ApplicationComponent extends ApplicationComponentExposes,
                                              ApplicationComponentInjects {

    final class Initializer {

        static public ApplicationComponent init(WeatherApplication weatherApplication) {
            return DaggerApplicationComponent.builder()
                                             .applicationModule(new ApplicationModule(weatherApplication))
                                             .dataModule(new DataModule())
                                             .domainModule(new DomainModule())
                                             .mapperModule(new MapperModule())
                                             .threadingModule(new ThreadingModule())
                                             .build();
        }

        private Initializer() {
        }
    }
}
