package com.example.capstonereisplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class SavableTrip(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        plannedDurationInMinutes: Int,
        transfers: Int,
        fromName: String,
        departureTime: Date,
        arrivalTime: Date,
        fromStops: Int,
        destinationName: String,
        cancelled: Boolean,
) {

}