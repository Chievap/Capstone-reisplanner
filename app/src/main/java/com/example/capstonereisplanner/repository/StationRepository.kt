package com.example.capstonereisplanner.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstonereisplanner.api.StationApi
import com.example.capstonereisplanner.api.StationApiService
import com.example.capstonereisplanner.model.StationResult

class StationRepository {
    private val stationApiService: StationApiService = StationApi.createApi()

    private val _stations: MutableLiveData<StationResult> = MutableLiveData()

    val stations: LiveData<StationResult> get() = _stations

    suspend fun getStations(){
        val stationList = stationApiService.getStations()
        _stations.value = stationList
    }
}