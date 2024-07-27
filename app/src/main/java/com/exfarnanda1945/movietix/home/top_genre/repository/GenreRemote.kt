package com.exfarnanda1945.movietix.home.top_genre.repository

data class GenreRemote(
    val id: Int,
    val name: String
)

sealed class GenreRemoteResult {
    data class Success(val genres: List<GenreRemote>) : GenreRemoteResult()
    data class Failure(val exception: Exception) : GenreRemoteResult()
}