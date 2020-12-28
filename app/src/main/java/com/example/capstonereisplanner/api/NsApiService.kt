package com.example.capstonereisplanner.api

import com.example.capstonereisplanner.results.StationSearchResult
import retrofit2.http.GET

interface NsApiService {
    @GET(".")
    suspend fun getStations(): StationSearchResult

    @GET(".")
    suspend fun getTrip()
}