package com.exfarnanda1945.movietix.home.top_rated.repository

import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.domainExceptionMapper
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.home.top_rated.domain.GetTopRatedFilmUseCase
import com.exfarnanda1945.movietix.home.top_rated.domain.TopRatedFilm
import com.exfarnanda1945.movietix.home.top_rated.domain.TopRatedFilmResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopRatedFilmUseCaseImpl(private val remote: GetTopRatedFilmRemoteRepository) :
    GetTopRatedFilmUseCase {
    override fun get(): Flow<TopRatedFilmResult> = flow {
        remote.get().collect { result ->
            when (result) {
                is RepositoryResult.Failure -> {
                    val exception = domainExceptionMapper(exception = result.exception)
                    emit(DomainResult.Error(exception))
                }
                is RepositoryResult.Success -> {
                    val mapToResult = result.data.map { it.toResult() }
                    emit(DomainResult.Success(mapToResult))
                }
            }
        }
    }


    private fun TopRatedFilmRemote.toResult() =TopRatedFilm(
        rated = this.rated,
        image = this.image,
        title = this.title,
        movieId = this.movieId
    )

}
