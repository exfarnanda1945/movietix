package com.exfarnanda1945.movietix.home.shared_discovery_film.repository

import com.exfarnanda1945.movietix.core.repository.RepositoryResult

data class DiscoveryFilmRemote(
    val movieId: Int,
    val title: String,
    val image: String,
    val releaseDate: String,
    val genres: List<Int>
)

typealias DiscoveryFilmRemoteResult = RepositoryResult<List<DiscoveryFilmRemote>, Exception>