package com.jdm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jdm.database.dao.MovieDao
import com.jdm.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class], version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}