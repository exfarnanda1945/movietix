package com.exfarnanda1945.movietix.home.top_genre.service

import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.repositoryExceptionMapper
import com.exfarnanda1945.movietix.home.top_genre.repository.GenreRemote
import com.exfarnanda1945.movietix.home.top_genre.repository.GenreRemoteResult
import com.exfarnanda1945.movietix.home.top_genre.repository.GetGenreRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGenreRemoteRepositoryImpl(private val service: GetGenreService) :
    GetGenreRemoteRepository {
    override fun get(): Flow<GenreRemoteResult> = flow {
        try {
            val result = service.get()
            emit(RepositoryResult.Success(
                result.genres.map { it.toRemoteResult() }
            ))

        } catch (e: Exception) {
            val result =
                repositoryExceptionMapper(exception = e, tagException = "GetGenreRemoteRepository")
            emit(RepositoryResult.Failure(result))
        }
    }


    private fun GenresItem.toRemoteResult(): GenreRemote = GenreRemote(
        id = this.id,
        name = this.name
    )
}

