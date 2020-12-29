package com.example.capstonereisplanner.converter

import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.model.Trip
import com.example.capstonereisplanner.model.tripDetail.Legs
import com.example.capstonereisplanner.model.tripDetail.Trips

class TripConverter {
    fun convertTrips(trip: Trip): List<SavableTrip> {
        val savableTrips = mutableListOf<SavableTrip>()
        //TODO: make legs dynamic
        for (trip in trip.trips) {
            savableTrips.add(SavableTrip(trip.actualDurationInMinutes,
                    trip.transfers,
                    trip.legs[0].origin.name,
                    trip.legs[0].origin.plannedDateTime,
                    trip.legs[1].origin.plannedDateTime,
                    trip.legs[0].stops.size,
                    trip.legs[1].origin.name,
                    checkCancelled(trip.legs)))
        }

        return savableTrips
    }

    private fun checkCancelled(legs: List<Legs>): Boolean {
        for (leg in legs) {
            if (!leg.cancelled) {
                return false
            }
        }
        return true
    }
}