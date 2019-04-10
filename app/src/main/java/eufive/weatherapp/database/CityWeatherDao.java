package eufive.weatherapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CityWeatherDao {

    @Query("select * from CityWeatherDbModel")
    Single<List<CityWeatherDbModel>> getAllData();

    @Query("select * from CityWeatherDbModel where id IN (:id)")
    Single<List<CityWeatherDbModel>> getItemsById(int[] id);

    @Query("select * from CityWeatherDbModel where id = :id")
    Single<CityWeatherDbModel> getItemById(int id);

    @Insert(onConflict = REPLACE)
    void insertMultipleData(CityWeatherDbModel... cityWeatherDbModels);
}
