package com.exfarnanda1945.movietix.home.top_rated.domain

import kotlinx.coroutines.flow.Flow

interface GetTopRatedFilmUseCase {
    fun get(): Flow<TopRatedFilmResult>
}