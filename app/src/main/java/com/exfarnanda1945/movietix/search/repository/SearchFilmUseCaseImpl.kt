package com.exfarnanda1945.movietix.search.repository

import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.domainExceptionMapper
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.search.domain.SearchFilm
import com.exfarnanda1945.movietix.search.domain.SearchFilmResult
import com.exfarnanda1945.movietix.search.domain.SearchFilmUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchFilmUseCaseImpl(private val remote: SearchFilmRemoteRepository) : SearchFilmUseCase {
    override fun get(query: String): Flow<SearchFilmResult> = flow {
        remote.get(query).collect { result ->
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

    private fun SearchFilmRemote.toResult(): SearchFilm = SearchFilm(
        image = this.image,
        rate = this.rate,
        movieId = this.movieId,
        title = this.title,
        releaseDate = this.releaseDate
    )
}
