package com.example.capstonereisplanner.api

import com.example.capstonereisplanner.model.StationResult
import retrofit2.http.GET

interface StationApiService {
    @GET(".")
    suspend fun getStations(): StationResult
}