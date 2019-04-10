package eufive.weatherapp.dagger.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import eufive.weatherapp.dagger.ComponentFactory;
import eufive.weatherapp.dagger.activity.DaggerActivity;

public abstract class DaggerFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getFragmentComponent());
    }

    public FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(this, getDaggerActivity().getActivityComponent());
        }

        return fragmentComponent;
    }

    protected abstract void inject(FragmentComponent fragmentComponent);

    public DaggerActivity getDaggerActivity() {
        return ((DaggerActivity) getActivity());
    }
}