package com.example.capstonereisplanner.api

import retrofit2.http.GET

interface NsApiService {
    @GET(".")
    suspend fun getStations():StationSearchResult
}