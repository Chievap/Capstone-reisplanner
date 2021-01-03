package com.example.capstonereisplanner.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstonereisplanner.dao.ActiveTripDao
import com.example.capstonereisplanner.database.ActiveTripRoomDatabase
import com.example.capstonereisplanner.entity.SavableTrip

class ActiveTripRepository(context: Context) {

    private val tripDao: ActiveTripDao

    init {
        val database = ActiveTripRoomDatabase.getDatabaseInstance(context)
        tripDao = database!!.tripDao()
    }

    fun getTripList(): LiveData<List<SavableTrip>> {
        return tripDao.getAllTrips()
    }

    suspend fun insertTrip(trip: SavableTrip) {
        tripDao.insertTrip(trip)
    }

    suspend fun deleteAllTrips() {
        tripDao.deleteAllTrips()
    }
}