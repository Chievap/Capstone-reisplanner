package com.example.capstonereisplanner.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SavableStation(
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val code: Long,
) {
}