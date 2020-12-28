package com.example.capstonereisplanner.converter

import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.model.Trips

class TripConverter {
    fun convertTrips(trips: Trips): List<SavableTrip> {
        val savableTrips = mutableListOf<SavableTrip>()
        //TODO: make legs dynamic
        for (trip in trips.trips) {
            savableTrips.add(SavableTrip(trip.actualDurationInMinutes,
                    trip.transfers,
                    trip.legs[0].origin.name,
                    trip.legs[0].origin.plannedDateTime,
                    trip.legs[1].origin.plannedDateTime,
                    trip.legs[0].stops.size,
                    trip.legs[1].origin.name,
                    trip.legs[0].cancelled))
        }

        return savableTrips
    }
}