package com.example.ebay_2022.api

import com.example.ebay_2022.data.Earthquake

interface EarthquakeApi {

    companion object {
        const val BASE_URL = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman"
    }

    suspend fun getEarthquakes(): List<Earthquake>
}