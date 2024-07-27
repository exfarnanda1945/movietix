package com.exfarnanda1945.movietix.home.shared_discovery_film.domain

import kotlinx.coroutines.flow.Flow

interface GetDiscoveryFilmUseCase {
    fun get(genreId:Int): Flow<DiscoveryFilmResult>
}