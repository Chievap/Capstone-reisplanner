package com.example.capstonereisplanner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.capstonereisplanner.R
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.model.StationResult
import com.example.capstonereisplanner.model.stationDetail.Payload
import com.example.capstonereisplanner.viewmodel.ActiveTripViewModel
import com.example.capstonereisplanner.viewmodel.StationViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.collections.HashMap

const val ZOOM_LEVEL = 7.0
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val activeTripViewModel: ActiveTripViewModel by viewModels()
    private val stationViewModel: StationViewModel by viewModels()
    private lateinit var currentTrip: List<SavableTrip>
    private lateinit var stationList: StationResult
    private var markers: HashMap<String, LatLng> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Fetch stations
        stationViewModel.getStations()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Check if the stations are found to use for the long and lat
        stationViewModel.stations.observe(this, {
            stationList = it
            if (this::stationList.isInitialized && this::currentTrip.isInitialized) {
                addMarkers(mMap)
            }

        })
        // Get the active trip
        activeTripViewModel.trips.observe(this, {
            currentTrip = it

            if (this::stationList.isInitialized && this::currentTrip.isInitialized) {
                addMarkers(mMap)
            }
        })
    }

    /**
     * Add the markers to the map
     */
    private fun addMarkers(mMap: GoogleMap) {
        val long = arrayListOf<Double>()
        val lat = arrayListOf<Double>()
        // Add the long and lat of the stations to the map
        for (payload: Payload in stationList.payload) {
            if (payload.namen.lang == currentTrip[0].fromName || payload.namen.lang == currentTrip[0].destinationName) {
                val marker = LatLng(payload.lat, payload.lng)
                long.add(payload.lng)
                lat.add(payload.lat)
                markers[payload.namen.lang] = marker
            }
        }
        // Add a marker for every entry in the map
        for (entry: MutableMap.MutableEntry<String, LatLng> in markers) {
            mMap.addMarker(MarkerOptions().position(entry.value).title(entry.key))
        }
        // Move the camera position to the middle of the given stations
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                calculateAndMoveCamera(
                    long[0],
                    lat[0], long[1], lat[1]
                ), ZOOM_LEVEL.toFloat()
            )
        )
    }

    private fun calculateAndMoveCamera(
        long1: Double,
        lat1: Double,
        long2: Double,
        lat2: Double
    ): LatLng {
        return LatLng((lat1 + lat2) / 2, (long1 + long2) / 2)
    }
}