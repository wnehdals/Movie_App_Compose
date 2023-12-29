package com.jdm.data.mapper

import com.jdm.model.Movie
import com.jdm.model.Search
import com.jdm.database.entity.MovieEntity
import com.jdm.network.model.MovieResp
import com.jdm.network.model.SearchResp

fun SearchResp.toSearch(): Search {
    val movies: List<Movie> =
        if (this.searches == null) emptyList()
        else this.searches!!.map { it.toMovie() }
    return Search(
        totalCnt = totalCnt?: 0,
        movies = movies
    )
}
fun MovieResp.toMovie(): Movie {
    return Movie(
        id = id?: "Unknown",
        title = title?: "Unknown",
        year = year?: "Unknown",
        poster = poster?: "",
        rank = 0
    )
}
fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        year = year,
        poster = poster,
        rank = rank
    )
}
fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        year = year,
        poster = poster,
        rank = rank
    )
}