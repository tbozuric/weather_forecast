package eufive.weatherapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {CityWeatherDbModel.class}, version = 1)
@TypeConverters({TimeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract CityWeatherDao getCityWeatherDao();
}
