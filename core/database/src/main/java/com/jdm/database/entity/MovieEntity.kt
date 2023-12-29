package com.jdm.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,             // id
    val title: String,          // 영화 제목
    val year: String,           // 시간
    val poster: String,         // 포스터
    val rank: Int               // 순위
)