package com.example.capstonereisplanner.viewmodel

import com.example.capstonereisplanner.model.StationResult
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstonereisplanner.converter.StationConverter
import com.example.capstonereisplanner.entity.SavableStation
import com.example.capstonereisplanner.repository.SavableStationRepository
import com.example.capstonereisplanner.repository.StationRepository
import com.example.capstonereisplanner.results.StationSearchResult
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