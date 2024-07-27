package com.exfarnanda1945.movietix.home.top_rated.repository

import com.exfarnanda1945.movietix.home.top_rated.domain.GetTopRatedFilmUseCase
import com.exfarnanda1945.movietix.home.top_rated.domain.TopRatedFilmResult
import kotlinx.coroutines.flow.Flow

class GetTopRatedFilmUseCaseImpl(val remoteRepository: GetTopRatedFilmRemoteRepository) :
    GetTopRatedFilmUseCase {
    override fun get(): Flow<TopRatedFilmResult> {
        TODO("Not yet implemented")
    }
}