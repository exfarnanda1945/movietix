package com.exfarnanda1945.movietix.home.shared_discovery_film.service

import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.repositoryExceptionMapper
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.DiscoveryFilmRemote
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.DiscoveryFilmRemoteResult
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.GetDiscoveryFilmRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDiscoveryFilmRemoteRepositoryImpl(private val service: GetDiscoveryFilmService) :
    GetDiscoveryFilmRemoteRepository {
    override fun get(genreId: Int): Flow<DiscoveryFilmRemoteResult> = flow {
        try {
            val result = service.get(genreId)
            emit(
                RepositoryResult.Success(result.results.map { it.toRemoteResult() })
            )
        } catch (e: Exception) {
            val exception = repositoryExceptionMapper(e, " GetDiscoveryFilmRemoteRepository ")
            emit(RepositoryResult.Failure(exception))
        }
    }

    private fun DiscoveryFilmResultsItem.toRemoteResult() = DiscoveryFilmRemote(
        title = this.title,
        image = this.posterPath,
        movieId = this.id,
        genres = this.genreIds,
        releaseDate = this.releaseDate
    )
}