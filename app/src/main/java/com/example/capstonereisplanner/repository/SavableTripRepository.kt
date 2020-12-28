package com.example.capstonereisplanner.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstonereisplanner.dao.TripDao
import com.example.capstonereisplanner.database.TripListRoomDatabase
import com.example.capstonereisplanner.entity.SavableTrip

class SavableTripRepository(context: Context) {

    private val tripDao: TripDao

    init {
        val database = TripListRoomDatabase.getDatabaseInstance(context)
        tripDao = database!!.tripDao()
    }

    fun getTripList(): LiveData<List<SavableTrip>>{
        return tripDao.getAllTrips()
    }

    suspend fun insertTrip(trip: SavableTrip){
        tripDao.insertTrip(trip)
    }

    suspend fun deleteAllTrips(){
        tripDao.deleteAllTrips()
    }
}