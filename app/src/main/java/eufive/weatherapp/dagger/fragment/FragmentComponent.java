package eufive.weatherapp.dagger.fragment;

import dagger.Component;
import eufive.weatherapp.dagger.activity.ActivityComponent;
import eufive.weatherapp.dagger.fragment.module.FragmentPresenterModule;

@FragmentScope
@Component(modules = {FragmentPresenterModule.class},
        dependencies = {ActivityComponent.class})
public interface FragmentComponent extends FragmentComponentExposes,
                                           FragmentComponentInjects {

    final class Initializer {

        static public FragmentComponent init(final DaggerFragment fragment, final ActivityComponent activityComponent) {
            return DaggerFragmentComponent.builder()
                                          .activityComponent(activityComponent)
                                          .fragmentPresenterModule(new FragmentPresenterModule(fragment))
                                          .build();
        }

        private Initializer() {
        }
    }
}

