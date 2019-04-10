package eufive.weatherapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eufive.weatherapp.adapters.ViewPagerAdapter;
import eufive.weatherapp.dagger.activity.ActivityComponent;
import eufive.weatherapp.dagger.activity.DaggerActivity;

public final class MainActivity extends DaggerActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;

    @Inject ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    private void setupViewPager() {
        viewPager.setAdapter(viewPagerAdapter);
    }
}