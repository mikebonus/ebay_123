package com.example.ebay_2022.di

import android.app.Application
import androidx.room.Room
import com.example.ebay_2022.api.EarthquakeApi
import com.example.ebay_2022.data.EarthquakeDatabase
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {

    // MODULE #1: retrofit-object
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(EarthquakeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // MODULE #2: earthquake-object
    @Provides
    @Singleton
    fun provideEarthquakeApi(retrofit: Retrofit): EarthquakeApi =
        retrofit.create(EarthquakeApi::class.java)


    // MODULE #3: d/b instance
    @Provides
    @Singleton
    fun provideDatabase(app: Application): EarthquakeDatabase =
        Room.databaseBuilder(
            app, EarthquakeDatabase::class.java,
            "earthquake database"
        )
            .build()
}