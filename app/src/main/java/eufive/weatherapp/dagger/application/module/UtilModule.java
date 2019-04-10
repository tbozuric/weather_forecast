package eufive.weatherapp.dagger.application.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eufive.weatherapp.dagger.application.ForApplication;
import eufive.weatherapp.image.ImageLoader;
import eufive.weatherapp.image.ImageLoaderPicasso;

@Module
public final class UtilModule {

    @Singleton
    @Provides
    ImageLoader provideImageLoader(@ForApplication final Context context) {
        return new ImageLoaderPicasso(context);
    }

    public interface Exposes {

        ImageLoader imageLoader();
    }
}
