package com.example.capstonereisplanner.converter

import com.example.capstonereisplanner.model.stationDetail.Payload
import com.example.capstonereisplanner.entity.SavableStation

class StationConverter {
    private fun convertStation(station: Payload): SavableStation {
        return SavableStation(
            name = station.namen.lang,
            country = station.land,
            code = station.code
        )
    }

    fun convertStations(stations: List<Payload>): List<SavableStation> {
        val savableStations = mutableListOf<SavableStation>()

        for (payload in stations) {
            savableStations.add(convertStation(payload))
        }
        return savableStations
    }
}