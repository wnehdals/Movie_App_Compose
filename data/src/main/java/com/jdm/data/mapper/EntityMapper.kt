package com.jdm.data.mapper

import com.jdm.domain.model.Movie
import com.jdm.data.entity.MovieEntity

fun Movie.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    year = year,
    type = type,
    poster = poster,
    rank = rank
)
fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    year = year,
    type = type,
    poster = poster,
    isFavorite = true,
    rank = rank
)