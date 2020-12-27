package com.example.capstonereisplanner.api

import com.example.capstonereisplanner.model.Station
import com.google.gson.annotations.SerializedName

class StationSearchResult {
    @SerializedName("results") var results = arrayListOf<Station>()
}