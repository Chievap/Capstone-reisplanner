package com.example.capstonereisplanner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstonereisplanner.dao.ActiveTripDao
import com.example.capstonereisplanner.entity.SavableTrip

@Database(entities = [SavableTrip::class], version = 1, exportSchema = false)
abstract class ActiveTripRoomDatabase : RoomDatabase() {

    abstract fun tripDao(): ActiveTripDao

    companion object {
        private const val DATABASE_NAME = "ACTIVE_TRIP_LIST_DATABASE"

        @Volatile
        private var tripListRoomDatabaseInstance: ActiveTripRoomDatabase? = null

        fun getDatabaseInstance(context: Context): ActiveTripRoomDatabase? {
            if (tripListRoomDatabaseInstance == null) {
                synchronized(ActiveTripRoomDatabase::class.java) {
                    if (tripListRoomDatabaseInstance == null) {
                        tripListRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ActiveTripRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return tripListRoomDatabaseInstance
        }
    }
}