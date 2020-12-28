package com.example.capstonereisplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SavableStation(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val name: String,
        val country: String,
        val code: String,
) {
}