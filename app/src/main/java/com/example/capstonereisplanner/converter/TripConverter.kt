package com.example.capstonereisplanner.converter

import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.model.Trip
import com.example.capstonereisplanner.model.tripDetail.Legs
import com.example.capstonereisplanner.model.tripDetail.Trips

class TripConverter {
    fun convertTrips(trip: Trip): List<SavableTrip> {
        val savableTrips = mutableListOf<SavableTrip>()
        for (trips in trip.trips) {
            savableTrips.add(SavableTrip(trips.actualDurationInMinutes,
                    trips.transfers,
                    trips.legs[0].origin.name,
                    trips.legs[0].origin.plannedDateTime,
                    trips.legs[0].destination.plannedDateTime,
                    trips.legs[0].stops.size,
                    trips.legs[0].destination.name,
                    checkCancelled(trips.legs)))
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