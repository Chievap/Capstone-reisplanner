package com.example.capstonereisplanner.model

import androidx.room.PrimaryKey

data class FavoriteTrip (
    val fromName: String,
    val toName: String,
    val fromCode: String,
    val toCode: String
)