package com.example.ebay_2022.repository

import com.example.ebay_2022.api.EarthquakeApi
import com.example.ebay_2022.data.Earthquake

class Repository(private val earthquakeApi: EarthquakeApi) {
    suspend fun getEarthquake(): List<Earthquake> {
        return earthquakeApi.getEarthquakes()
    }
}