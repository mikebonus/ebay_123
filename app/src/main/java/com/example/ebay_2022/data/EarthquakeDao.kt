package com.example.ebay_2022.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EarthquakeDao {

    // RETRIEVE
    @Query("SELECT * FROM earthquake")
    fun getAllEarthquake(): Flow<List<Earthquake>>

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarthquake(news: List<Earthquake>)

    // DELETE
    @Query("DELETE FROM earthquake")
    suspend fun deleteAllEarthquake()
}