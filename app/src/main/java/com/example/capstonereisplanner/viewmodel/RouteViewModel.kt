package com.example.capstonereisplanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.repository.SavableTripRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RouteViewModel(application: Application) : AndroidViewModel(application) {
    private val savableTripRepository = SavableTripRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val trips: LiveData<List<SavableTrip>> = savableTripRepository.getTripList()

    fun saveTrip(savableTrip: SavableTrip) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                savableTripRepository.insertTrip(savableTrip)
            }
        }
    }
}