package com.jdm.data.model

import com.google.gson.annotations.SerializedName
import com.jdm.domain.model.Movie

data class MovieResp(
    @SerializedName("imdbID")
    val id: String = "",
    @SerializedName("Title")
    val title: String = "",
    @SerializedName("Year")
    val year: String = "",
    @SerializedName("Type")
    val type: String = "",
    @SerializedName("Poster")
    val poster: String = ""
)
internal fun MovieResp.toDomain() = Movie(
    id = id,
    title = title,
    year = year,
    type = type,
    poster = poster
)
