package com.exfarnanda1945.movietix.home.shared_discovery_film.repository

import kotlinx.coroutines.flow.Flow

interface GetDiscoveryFilmRemoteRepository {
    fun get(
        genreId: Int? = null,
        originCountry: String? = null
    ): Flow<DiscoveryFilmRemoteResult>
}