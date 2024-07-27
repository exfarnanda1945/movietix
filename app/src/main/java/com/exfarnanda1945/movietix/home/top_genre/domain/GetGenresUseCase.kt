package com.exfarnanda1945.movietix.home.top_genre.domain

import kotlinx.coroutines.flow.Flow

interface GetGenresUseCase {
    fun get(): Flow<GenreResult>
}