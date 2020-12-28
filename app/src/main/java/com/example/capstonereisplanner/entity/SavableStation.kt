package com.example.capstonereisplanner.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavableStation")
class SavableStation(
        val name: String,
        val country: String,
        val code: String,
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
)