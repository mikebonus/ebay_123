package com.example.ebay_2022.api

import com.example.ebay_2022.data.Earthquake
import com.example.ebay_2022.ui.LaunchActivity
import org.json.JSONObject
import java.io.BufferedInputStream
import java.net.URL

class ParsingImpl : EarthquakeApi {

    val TAG = "TAG"

    companion object {
        val listEarthquake = arrayListOf<Earthquake>()
    }

    override suspend fun getEarthquakes(): List<Earthquake> {

        val url =
            URL("http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman")
        val connection = url.openConnection()
        connection.connect()

        val bufferedInputStream = BufferedInputStream(connection.getInputStream())
        val bufferedReader = bufferedInputStream.bufferedReader()

        val stringBuffer = StringBuffer()
        for (line in bufferedReader.readLines()) {
            stringBuffer.append(line)
        }

        bufferedReader.close()
        val fullJson = stringBuffer.toString()

        // JSON-Parsing
        val jsonObjectEarthquakes = JSONObject(fullJson)
        val jsonArray = jsonObjectEarthquakes.getJSONArray("earthquakes")

        val listOne = arrayListOf<Earthquake>()
        val jsonArrayLength = jsonArray.length()

        if (LaunchActivity.thisSelectedName == "SERIOUS") {
            for (i in 0 until jsonArrayLength) {
                if (jsonArray.getJSONObject(i).getDouble("magnitude") > 8.0) {
                    val eqdatetime = jsonArray.getJSONObject(i).getString("datetime")
                    val eqdepth = jsonArray.getJSONObject(i).getDouble("depth")
                    val eqlng = jsonArray.getJSONObject(i).getDouble("lng")
                    val eqsrc = jsonArray.getJSONObject(i).getString("src")
                    val eqid = jsonArray.getJSONObject(i).getString("eqid")
                    val eqmagnitude = jsonArray.getJSONObject(i).getDouble("magnitude")
                    val eqlat = jsonArray.getJSONObject(i).getDouble("lat")

                    val employeeJustine = Earthquake(
                        eqdatetime,
                        eqdepth,
                        eqlng,
                        eqsrc,
                        eqid,
                        eqmagnitude,
                        eqlat,
                    )
                    listEarthquake.add(employeeJustine)
                }
            }
            return listEarthquake
        }

        for (i in 0 until jsonArrayLength) {

            val eqdatetime = jsonArray.getJSONObject(i).getString("datetime")
            val eqdepth = jsonArray.getJSONObject(i).getDouble("depth")
            val eqlng = jsonArray.getJSONObject(i).getDouble("lng")
            val eqsrc = jsonArray.getJSONObject(i).getString("src")
            val eqid = jsonArray.getJSONObject(i).getString("eqid")
            val eqmagnitude = jsonArray.getJSONObject(i).getDouble("magnitude")
            val eqlat = jsonArray.getJSONObject(i).getDouble("lat")

            val thisEarthquake = Earthquake(
                eqdatetime,
                eqdepth,
                eqlng,
                eqsrc,
                eqid,
                eqmagnitude,
                eqlat
            )

            listOne.add(thisEarthquake)
        }

        return listOne

    }
}