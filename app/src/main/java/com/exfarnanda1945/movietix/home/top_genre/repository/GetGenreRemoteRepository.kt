package com.exfarnanda1945.movietix.home.top_genre.repository

import kotlinx.coroutines.flow.Flow

interface GetGenreRemoteRepository {
    fun get(): Flow<GenreRemoteResult>
}