package com.example.capstonereisplanner.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstonereisplanner.model.FavoriteTrip
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout

class FavoriteTripRepository {

    private var fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var tripDocument = fireStore.collection("trips").document("FavoriteTrip")

    private var _favoriteTrips: MutableLiveData<FavoriteTrip> = MutableLiveData()

    val favoriteTrip: LiveData<FavoriteTrip> get() = _favoriteTrips

    private val _createSuccess: MutableLiveData<Boolean> = MutableLiveData()

    val createSuccess: LiveData<Boolean> get() = _createSuccess

    suspend fun getFavoriteTrips(){
        try{
            withTimeout(5_000){
                val data = tripDocument.get().await()

                val fromName = data.getString("fromName").toString()
                val toName = data.getString("toName").toString()
                val fromCode = data.getString("fromCode").toString()
                val toCode = data.getString("toCode").toString()

                _favoriteTrips.value = FavoriteTrip(fromName,toName,fromCode,toCode)
            }
        } catch(e:Exception){

        }
    }

    suspend fun createFavoriteTrip(favoriteTrip: FavoriteTrip){
        try{
            withTimeout(5_000){
                tripDocument.set(favoriteTrip).await()

                _createSuccess.value = true
            }
        }catch (e: Exception){

        }
    }
}