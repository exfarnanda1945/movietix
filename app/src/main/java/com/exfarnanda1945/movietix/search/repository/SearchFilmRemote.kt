package com.exfarnanda1945.movietix.search.repository

import com.exfarnanda1945.movietix.core.repository.RepositoryResult

data class SearchFilmRemote(
    val movieId: Int,
    val title: String,
    val rate: Double,
    val image: String,
    val releaseDate: String
)

typealias SearchFilmRemoteResult = RepositoryResult<List<SearchFilmRemote>, Exception>