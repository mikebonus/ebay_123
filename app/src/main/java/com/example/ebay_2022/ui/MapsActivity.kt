package com.example.ebay_2022.ui

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ebay_2022.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.ebay_2022.databinding.ActivityMapsBinding
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var intentReceivedOne: String? = null
    var receivedLatitude: String? = null
    var receivedLongitude: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent-received
        intentReceivedOne = intent.getStringExtra("source")

        // Geo-Converter (converts the location into <lat, lon>)
        val address = intentReceivedOne
        val geocode = Geocoder(this, Locale.getDefault())
        val addList = geocode.getFromLocationName(address, 1)
        receivedLatitude = addList[0].latitude.toString()
        receivedLongitude = addList[0].longitude.toString()


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val targetLocation = LatLng(receivedLatitude?.toDouble()!!, receivedLongitude?.toDouble()!!)
        mMap.addMarker(MarkerOptions().position(targetLocation).title(targetLocation.toString()))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(targetLocation))
    }
}