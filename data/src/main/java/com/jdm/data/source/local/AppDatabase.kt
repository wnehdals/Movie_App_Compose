package com.jdm.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jdm.data.entity.MovieEntity
import com.jdm.data.source.local.dao.MovieDao

@Database(
    entities = [MovieEntity::class], version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}