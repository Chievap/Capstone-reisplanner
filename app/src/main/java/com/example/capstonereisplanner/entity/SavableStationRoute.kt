package com.example.capstonereisplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavableStationRoute")
class SavableStationRoute(
    val name: String,
    val track: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
)