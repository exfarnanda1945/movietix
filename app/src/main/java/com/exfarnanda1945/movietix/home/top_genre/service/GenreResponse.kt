package com.exfarnanda1945.movietix.home.top_genre.service

import com.squareup.moshi.Json

data class GenreResponse(

    @Json(name = "genres")
    val genres: List<GenresItem> = listOf()
)

data class GenresItem(

    @Json(name = "name")
    val name: String = "",

    @Json(name = "id")
    val id: Int = 0
)
