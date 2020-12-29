package com.example.capstonereisplanner.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstonereisplanner.api.TripApi
import com.example.capstonereisplanner.model.Trip

class TripRepository {
    private val tripApiService = TripApi.createApi()

    private val _trip: MutableLiveData<Trip> = MutableLiveData()

    val trip: LiveData<Trip> get() = _trip

    suspend fun getTrip(fromStation: String, toStation: String){
        val trip = tripApiService.getTrip(fromStation, toStation)
        _trip.value = trip
    }
}