package com.exfarnanda1945.movietix.home.top_genre.repository

import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.domainExceptionMapper
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.home.top_genre.domain.Genre
import com.exfarnanda1945.movietix.home.top_genre.domain.GenreResult
import com.exfarnanda1945.movietix.home.top_genre.domain.GetGenresUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGenreUseCaseImpl(private val remote: GetGenreRemoteRepository) : GetGenresUseCase {
    override fun get(): Flow<GenreResult> = flow {
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

    private fun GenreRemote.toResult(): Genre = Genre(
        id = this.id,
        name = this.name
    )
}
