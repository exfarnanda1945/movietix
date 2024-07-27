package com.exfarnanda1945.movietix.home.shared_discovery_film.domain

import com.exfarnanda1945.movietix.core.domain.DomainResult

data class DiscoveryFilm(
    val movieId: Int,
    val title: String,
    val image: String,
    val releaseDate: String,
    val genres: List<Int>
)

typealias DiscoveryFilmResult = DomainResult<List<DiscoveryFilm>, Exception>