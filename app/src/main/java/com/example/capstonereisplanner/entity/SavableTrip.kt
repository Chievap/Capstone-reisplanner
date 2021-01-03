package com.example.capstonereisplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavableTrip")
class SavableTrip(
        val plannedDurationInMinutes: Int,
        val transfers: Int,
        val fromName: String,
        val departureTime: String,
        val arrivalTime: String,
        val fromStops: Int,
        val destinationName: String,
        val cancelled: Boolean,
        val fromTrack: Int,
        val toTrack: Int,
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
)