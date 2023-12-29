package com.jdm.database.dao

import androidx.room.*
import com.jdm.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Update
    suspend fun updateAll(movieEntities: List<MovieEntity>)

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("SELECT * FROM MOVIE ORDER BY RANK ASC" )
    suspend fun loadAll(): List<MovieEntity>
}