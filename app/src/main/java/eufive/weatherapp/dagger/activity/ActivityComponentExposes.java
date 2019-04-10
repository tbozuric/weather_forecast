package eufive.weatherapp.dagger.activity;

import eufive.weatherapp.dagger.activity.module.ActivityModule;
import eufive.weatherapp.dagger.activity.module.ActivityPresenterModule;
import eufive.weatherapp.dagger.application.ApplicationComponentExposes;

public interface ActivityComponentExposes extends ApplicationComponentExposes,
                                                  ActivityModule.Exposes,
                                                  ActivityPresenterModule.Exposes { }
