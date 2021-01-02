package com.example.capstonereisplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "SavableStationRoute")
class SavableStationRoute(
    val name: String,
    val track: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
) {
}