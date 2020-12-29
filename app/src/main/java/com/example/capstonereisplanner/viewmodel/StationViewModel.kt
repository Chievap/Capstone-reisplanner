package com.example.capstonereisplanner.viewmodel

import com.example.capstonereisplanner.model.StationResult
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.capstonereisplanner.converter.StationConverter
import com.example.capstonereisplanner.repository.SavableStationRepository
import com.example.capstonereisplanner.repository.StationRepository
import kotlinx.coroutines.*

class StationViewModel(application: Application) : AndroidViewModel(application) {

    private val stationRepository = StationRepository()
    private val savableStationRepository = SavableStationRepository(application.applicationContext)

    private val stationConverter = StationConverter()

    private val mainScope = CoroutineScope(Dispatchers.Main)

    val stations: LiveData<StationResult> = stationRepository.stations

    fun getStations() {
        /*if (stationList.value != null) {
            return stationList.value
        }
        println("Starting fetch")
        var stations: StationSearchResult? = StationSearchResult()
        mainScope.launch {
            withContext(Dispatchers.IO) {
                stations = fetchStations().value
            }
        }
        println("fetch done")
        stations?.results?.forEach { station: Station ->
            run {
                mainScope.launch {
                    val converted = stationConverter.convertStation(station)
                    withContext(Dispatchers.IO) {
                        savableStationRepository.insertStation(converted)
                    }
                }
            }
        }

        return stations?.let { stationConverter.convertStations(stations = it.results) } */
        viewModelScope.launch {
            stationRepository.getStations()
        }

    }

    /* private suspend fun fetchStations(): MutableLiveData<StationSearchResult> {
        var returnValue: MutableLiveData<StationSearchResult> = MutableLiveData()
        val job = viewModelScope.async {
            returnValue = stationRepository.getStations()
        }
        job.await()
        return returnValue
    } */
}