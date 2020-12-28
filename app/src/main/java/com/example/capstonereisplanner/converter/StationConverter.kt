package com.example.capstonereisplanner.converter

import com.example.capstonereisplanner.entity.SavableStation
import com.example.capstonereisplanner.model.Station

class StationConverter {
    fun convertStation(station:Station): SavableStation {
        return SavableStation(name = station.getFullName(),country = station.land,code = station.code)
    }

    fun convertStations(stations: List<Station>): List<SavableStation>{
        val savableStations = mutableListOf<SavableStation>()

        for (station in stations){
            savableStations.add(convertStation(station))
        }
        return savableStations
    }
}