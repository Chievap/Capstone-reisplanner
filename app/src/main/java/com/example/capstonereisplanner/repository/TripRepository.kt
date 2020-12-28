package com.example.capstonereisplanner.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstonereisplanner.api.TripApi
import com.example.capstonereisplanner.model.Trips

class TripRepository {
    private val tripApiService = TripApi.createApi()

    private val _trips: MutableLiveData<Trips> = MutableLiveData()

    val trips: LiveData<Trips> get() = _trips

    suspend fun getTrip(fromStation: String, toStation: String){
        val trip = tripApiService.getTrip(fromStation, toStation)
        _trips.value = trip
    }
}