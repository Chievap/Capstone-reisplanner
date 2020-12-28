package com.example.capstonereisplanner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstonereisplanner.dao.TripDao
import com.example.capstonereisplanner.entity.SavableTrip

@Database(entities = [SavableTrip::class], version = 1, exportSchema = false)
abstract class TripListRoomDatabase: RoomDatabase() {

    abstract fun tripDao(): TripDao

    companion object{
        private const val DATABASE_NAME = "TRIP_LIST_DATABASE"

        @Volatile
        private var tripListRoomDatabaseInstance: TripListRoomDatabase? = null

        fun getDatabaseInstance(context: Context): TripListRoomDatabase? {
            if(tripListRoomDatabaseInstance == null) {
                synchronized(TripListRoomDatabase::class.java){
                    if(tripListRoomDatabaseInstance == null){
                        tripListRoomDatabaseInstance = Room.databaseBuilder(
                                context.applicationContext,
                                TripListRoomDatabase::class.java,
                                DATABASE_NAME
                        ).build()
                    }
                }
            }
            return tripListRoomDatabaseInstance
        }
    }
}