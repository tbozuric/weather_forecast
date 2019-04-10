package eufive.weatherapp.viewModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public final class CityWeatherViewModel implements Parcelable {

    public final int id;
    public final String nameOfCity;
    public final double minimumTemperature;
    public final double maximumTemperature;
    public final String icon;
    public final Date lastUpdated;

    public CityWeatherViewModel(final int id, final String nameOfCity, final double minimumTemperature, final double maximumTemperature, final String icon, final Date date) {
        this.id = id;
        this.nameOfCity = nameOfCity;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.icon = icon;
        this.lastUpdated = date;
    }

    protected CityWeatherViewModel(Parcel in) {
        id = in.readInt();
        nameOfCity = in.readString();
        minimumTemperature = in.readDouble();
        maximumTemperature = in.readDouble();
        icon = in.readString();
        lastUpdated = (java.util.Date) in.readSerializable();
    }

    public static final Creator<CityWeatherViewModel> CREATOR = new Creator<CityWeatherViewModel>() {

        @Override
        public CityWeatherViewModel createFromParcel(Parcel in) {
            return new CityWeatherViewModel(in);
        }

        @Override
        public CityWeatherViewModel[] newArray(int size) {
            return new CityWeatherViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeInt(id);
        parcel.writeString(nameOfCity);
        parcel.writeDouble(maximumTemperature);
        parcel.writeDouble(minimumTemperature);
        parcel.writeString(icon);
        parcel.writeSerializable(lastUpdated);
    }
}
