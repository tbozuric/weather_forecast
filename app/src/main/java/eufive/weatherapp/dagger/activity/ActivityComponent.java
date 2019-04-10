package eufive.weatherapp.dagger.activity;

import dagger.Component;
import eufive.weatherapp.dagger.activity.module.ActivityAdapterModule;
import eufive.weatherapp.dagger.activity.module.ActivityModule;
import eufive.weatherapp.dagger.activity.module.ActivityPresenterModule;
import eufive.weatherapp.dagger.application.ApplicationComponent;

@ActivityScope
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {ActivityModule.class,
                   ActivityPresenterModule.class,
                   ActivityAdapterModule.class})
public interface ActivityComponent extends ActivityComponentExposes,
                                           ActivityComponentInjects {

    final class Initializer {

        static public ActivityComponent init(final DaggerActivity daggerActivity, final ApplicationComponent applicationComponent) {
            return DaggerActivityComponent.builder()
                                          .applicationComponent(applicationComponent)
                                          .activityModule(new ActivityModule(daggerActivity))
                                          .activityPresenterModule(new ActivityPresenterModule(daggerActivity))
                                          .activityAdapterModule(new ActivityAdapterModule())
                                          .build();
        }

        private Initializer() {

        }
    }
}
