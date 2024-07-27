package com.exfarnanda1945.movietix.home.top_rated.repository

import kotlinx.coroutines.flow.Flow

interface GetTopRatedFilmRemoteRepository {
    fun get():Flow<TopRatedFilmRemoteResult>
}