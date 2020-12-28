package com.example.capstonereisplanner.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.capstonereisplanner.entity.SavableTrip

@Dao
interface TripDao {
    @Query("SELECT * FROM SavableTrip")
    fun getAllTrips(): LiveData<List<SavableTrip>>

    @Insert
    suspend fun insertTrip(trip: SavableTrip)

    @Query("DELETE FROM SavableTrip")
    suspend fun deleteAllTrips()
}