package com.example.capstonereisplanner.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SavableStation(
    val name: String,
    val country: String,
    val code: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
) {
}