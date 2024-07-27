package com.exfarnanda1945.movietix.home.top_genre.repository

import com.exfarnanda1945.movietix.core.repository.RepositoryResult

data class GenreRemote(
    val id: Int,
    val name: String
)

typealias GenreRemoteResult = RepositoryResult<List<GenreRemote>,Exception>