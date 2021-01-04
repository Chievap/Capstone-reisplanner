package com.example.capstonereisplanner.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.capstonereisplanner.model.FavoriteTrip
import com.example.capstonereisplanner.repository.FavoriteTripRepository
import kotlinx.coroutines.launch

class FavoriteTripViewModel(application: Application) : AndroidViewModel(application) {
    private val tag = "FIRESTORE"
    private val favoriteTripRepository: FavoriteTripRepository = FavoriteTripRepository()

    val favoriteTrip: LiveData<FavoriteTrip> = favoriteTripRepository.favoriteTrip

    val createSuccess: LiveData<Boolean> = favoriteTripRepository.createSuccess

    fun getFavoriteTrip(){
        viewModelScope.launch {
            try {
                favoriteTripRepository.getFavoriteTrips()
            }catch (e: Exception){
                Log.e(tag, e.toString() )
            }
        }
    }

    fun createFavoriteTrip(favoriteTrip: FavoriteTrip){
        viewModelScope.launch {
            try {
                favoriteTripRepository.createFavoriteTrip(favoriteTrip)
            }catch (e: Exception){
                Log.e(tag, e.toString() )
            }
        }
    }
}

