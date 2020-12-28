package com.example.capstonereisplanner.api

import com.example.capstonereisplanner.model.Trips
import retrofit2.http.GET
import retrofit2.http.Query

interface TripApiService {
    @GET(".")
    suspend fun getTrip(
            @Query("fromStation") fromStation: String,
            @Query("toStation") toStation: String
    ):Trips
}