package com.example.capstonereisplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonereisplanner.model.Trip
import androidx.lifecycle.viewModelScope
import com.example.capstonereisplanner.repository.TripRepository
import kotlinx.coroutines.launch

class TripViewModel: ViewModel() {

    private val tripRepository = TripRepository()

    val trip: LiveData<Trip> = tripRepository.trip

    fun getTrip(fromStation: String, toStation: String) {
        viewModelScope.launch {
            tripRepository.getTrip(fromStation, toStation)
        }
    }
}