package com.example.ebay_2022.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Earthquake::class],
    version = 1
)
abstract class EarthquakeDatabase : RoomDatabase() {

    abstract fun earthquakeDao(): EarthquakeDao

}
