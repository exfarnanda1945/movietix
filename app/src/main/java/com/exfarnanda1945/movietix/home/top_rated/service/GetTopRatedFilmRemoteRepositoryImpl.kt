package com.exfarnanda1945.movietix.home.top_rated.service

import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.repositoryExceptionMapper
import com.exfarnanda1945.movietix.home.top_rated.repository.GetTopRatedFilmRemoteRepository
import com.exfarnanda1945.movietix.home.top_rated.repository.TopRatedFilmRemote
import com.exfarnanda1945.movietix.home.top_rated.repository.TopRatedFilmRemoteResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetTopRatedFilmRemoteRepositoryImpl(private val service: GetTopRatedFilmService) :
    GetTopRatedFilmRemoteRepository {
    override fun get(): Flow<TopRatedFilmRemoteResult> = flow {
        try {
            val result = service.get()
            emit(
                RepositoryResult.Success(result.results.map { it.toRemoteModel() })
            )
        } catch (e: Exception) {
            val exception = repositoryExceptionMapper(e, "GetTopRatedFilmRemoteRepositoryImpl")
            emit(RepositoryResult.Failure(exception))
        }
    }.flowOn(Dispatchers.IO)

    private fun TopRatedFilmResultsItem.toRemoteModel() = TopRatedFilmRemote(
        image = this.posterPath,
        title = this.title,
        movieId = this.id,
        rated = this.voteAverage
    )
}