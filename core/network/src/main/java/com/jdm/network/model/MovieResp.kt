package com.jdm.network.model

import com.google.gson.annotations.SerializedName

data class MovieResp(
    @SerializedName("imdbID")
    val id: String? = "",
    @SerializedName("Title")
    val title: String? = "",
    @SerializedName("Year")
    val year: String? = "",
    @SerializedName("Type")
    val type: String? = "",
    @SerializedName("Poster")
    val poster: String? = ""
)