package com.example.capstonereisplanner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstonereisplanner.dao.StationDao
import com.example.capstonereisplanner.entity.SavableStation

@Database(entities = [SavableStation::class], version = 1, exportSchema = false)
abstract class StationListRoomDatabase : RoomDatabase() {

    abstract fun stationDao(): StationDao

    companion object {
        private const val DATABASE_NAME = "GAMES_LIST_DATABASE"

        @Volatile
        private var stationListRoomDatabaseInstance: StationListRoomDatabase? = null

        fun getDatabaseInstance(context: Context): StationListRoomDatabase? {
            if (stationListRoomDatabaseInstance == null) {
                synchronized(StationListRoomDatabase::class.java) {
                    if (stationListRoomDatabaseInstance == null) {
                        stationListRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            StationListRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return stationListRoomDatabaseInstance
        }
    }
}