package com.example.capstonereisplanner.api

import com.example.capstonereisplanner.results.StationSearchResult
import retrofit2.http.GET

interface StationApiService {
    @GET(".")
    suspend fun getStations(): StationSearchResult
}