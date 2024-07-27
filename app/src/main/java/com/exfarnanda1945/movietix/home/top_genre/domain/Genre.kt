package com.exfarnanda1945.movietix.home.top_genre.domain

import java.lang.Exception

data class Genre(
    val id: Int,
    val name: String
)

sealed class GenreResult {
    data class Success(val genres: List<Genre>) : GenreResult()
    data class Failure(val exception: Exception) : GenreResult()
}