package com.example.capstonereisplanner.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.capstonereisplanner.entity.SavableStation

@Dao
interface StationDao {

    @Query("SELECT * FROM SavableStation")
    fun getAllStations(): LiveData<List<SavableStation>>

    @Insert
    suspend fun insertStation(station: SavableStation)

    @Query("DELETE FROM SavableStation")
    suspend fun deleteAllStations()

}