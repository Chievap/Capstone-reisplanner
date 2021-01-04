package com.example.capstonereisplanner.viewmodel

import com.example.capstonereisplanner.model.StationResult
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.capstonereisplanner.repository.StationRepository
import kotlinx.coroutines.*

class StationViewModel(application: Application) : AndroidViewModel(application) {

    private val stationRepository = StationRepository()

    val stations: LiveData<StationResult> = stationRepository.stations

    fun getStations() {
        viewModelScope.launch {
            stationRepository.getStations()
        }

    }
}