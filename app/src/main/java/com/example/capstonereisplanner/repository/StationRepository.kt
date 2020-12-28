package com.example.capstonereisplanner.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstonereisplanner.api.NsApi
import com.example.capstonereisplanner.api.NsApiService
import com.example.capstonereisplanner.results.StationSearchResult

class StationRepository {
    private val nsApiService: NsApiService = NsApi.createApi()

    private val _stations: MutableLiveData<StationSearchResult> = MutableLiveData()

    val stations: LiveData<StationSearchResult> get() = _stations

    suspend fun getStations(): MutableLiveData<StationSearchResult>{
        val stationList = nsApiService.getStations()
        _stations.value = stationList
        return _stations
    }
}