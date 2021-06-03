package com.itis.weather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SimpleSQLiteQuery
import com.itis.weather.data.database.dao.WeatherDao
import com.itis.weather.data.database.entity.WeatherDb

@Database(
    entities = [WeatherDb::class],
    version = 4
)

abstract class KraDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    fun clearAndResetAllTables() {
        // reset all auto-incrementalValues
        val query = SimpleSQLiteQuery("DELETE FROM sqlite_sequence")

        runInTransaction {
            clearAllTables()
            query(query)
        }
    }
}