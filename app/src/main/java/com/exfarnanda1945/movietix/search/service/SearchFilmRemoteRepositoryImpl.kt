package com.exfarnanda1945.movietix.search.service

import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.repositoryExceptionMapper
import com.exfarnanda1945.movietix.search.repository.SearchFilmRemote
import com.exfarnanda1945.movietix.search.repository.SearchFilmRemoteRepository
import com.exfarnanda1945.movietix.search.repository.SearchFilmRemoteResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchFilmRemoteRepositoryImpl(private val service: SearchFilmService) :
    SearchFilmRemoteRepository {
    override fun get(query: String): Flow<SearchFilmRemoteResult> = flow {
        try {
            val result = service.get(query)
            val mapToRemote = result.results?.map { it.toRemoteModel() } ?: emptyList()
            emit(RepositoryResult.Success(mapToRemote))
        } catch (e: Exception) {
            val repoException = repositoryExceptionMapper(
                exception = e,
                tagException = "SearchFilmRemoteRepository"
            )
            emit(RepositoryResult.Failure(repoException))
        }
    }

    private fun SearchResultsItem?.toRemoteModel() = SearchFilmRemote(
        title = this?.title ?: "",
        rate = this?.voteAverage ?: 0.0,
        image = this?.posterPath ?: "",
        releaseDate = this?.releaseDate ?: "",
        movieId = this?.id ?: 0
    )
}
