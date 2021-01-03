package com.example.capstonereisplanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.repository.ActiveTripRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActiveTripViewModel(application: Application) : AndroidViewModel(application) {
    private val activeTripRepository = ActiveTripRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val trips: LiveData<List<SavableTrip>> = activeTripRepository.getTripList()

    fun saveTrip(savableTrip: SavableTrip) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                activeTripRepository.insertTrip(savableTrip)
            }
        }
    }

    fun deleteTrips() {
        mainScope.launch { withContext(Dispatchers.IO) { activeTripRepository.deleteAllTrips() } }
    }
}