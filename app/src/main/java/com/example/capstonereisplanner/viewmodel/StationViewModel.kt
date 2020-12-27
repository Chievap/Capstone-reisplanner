package com.example.capstonereisplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonereisplanner.api.StationSearchResult
import androidx.lifecycle.viewModelScope
import com.example.capstonereisplanner.repository.StationRepository
import kotlinx.coroutines.launch

class StationViewModel: ViewModel() {

    private val stationRepository = StationRepository()

    val stations: LiveData<StationSearchResult> = stationRepository.stations
    fun getStations(){
        viewModelScope.launch{
            stationRepository.getStations()
        }
    }
}