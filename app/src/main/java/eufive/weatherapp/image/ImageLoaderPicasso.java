package eufive.weatherapp.image;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public final class ImageLoaderPicasso implements ImageLoader {

    private final Context context;

    public ImageLoaderPicasso(final Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(final String url, final ImageView imageView) {

        Picasso.with(context).load(url).into(imageView);
    }
}
