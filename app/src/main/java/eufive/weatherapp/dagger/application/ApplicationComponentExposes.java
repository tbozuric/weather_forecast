package eufive.weatherapp.dagger.application;

import eufive.weatherapp.dagger.application.module.ApplicationModule;
import eufive.weatherapp.dagger.application.module.DataModule;
import eufive.weatherapp.dagger.application.module.DomainModule;
import eufive.weatherapp.dagger.application.module.MapperModule;
import eufive.weatherapp.dagger.application.module.ThreadingModule;
import eufive.weatherapp.dagger.application.module.UtilModule;

public interface ApplicationComponentExposes extends ApplicationModule.Exposes,
                                                     DataModule.Exposes,
                                                     DomainModule.Exposes,
                                                     MapperModule.Exposes,
                                                     UtilModule.Exposes,
                                                     ThreadingModule.Exposes { }
