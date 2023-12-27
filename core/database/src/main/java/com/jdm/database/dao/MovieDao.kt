package com.jdm.database.dao

import androidx.room.*
import com.jdm.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: MovieEntity)

    @Update
    fun updateAll(movieEntities: List<MovieEntity>)

    @Delete
    fun delete(movieEntity: MovieEntity)

    @Query("SELECT * FROM MOVIE ORDER BY RANK ASC" )
    fun loadAll(): Flow<List<MovieEntity>>
}