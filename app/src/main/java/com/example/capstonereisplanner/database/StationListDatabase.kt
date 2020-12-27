package com.example.capstonereisplanner.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.capstonereisplanner.dao.StationDao
import com.example.capstonereisplanner.model.Station

@Database(entities = [Station::class], version = 1, exportSchema = false)
abstract class StationListDatabase: RoomDatabase() {

    abstract fun stationDao(): StationDao

    companion object{

    }
}