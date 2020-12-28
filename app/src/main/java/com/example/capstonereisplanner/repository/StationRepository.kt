package com.example.capstonereisplanner.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstonereisplanner.api.StationApi
import com.example.capstonereisplanner.api.StationApiService
import com.example.capstonereisplanner.results.StationSearchResult

class StationRepository {
    private val stationApiService: StationApiService = StationApi.createApi()

    private val _stations: MutableLiveData<StationSearchResult> = MutableLiveData()

    val stations: LiveData<StationSearchResult> get() = _stations

    suspend fun getStations(): MutableLiveData<StationSearchResult>{
        val stationList = stationApiService.getStations()
        _stations.value = stationList
        return _stations
    }
}