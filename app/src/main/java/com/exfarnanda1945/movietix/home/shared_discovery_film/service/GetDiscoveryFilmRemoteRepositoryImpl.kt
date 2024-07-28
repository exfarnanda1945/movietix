package com.exfarnanda1945.movietix.home.shared_discovery_film.service

import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.repositoryExceptionMapper
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.DiscoveryFilmRemote
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.DiscoveryFilmRemoteResult
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.GetDiscoveryFilmRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetDiscoveryFilmRemoteRepositoryImpl(private val service: GetDiscoveryFilmService) :
    GetDiscoveryFilmRemoteRepository {
    override fun get(genreId: Int?, originCountry: String?): Flow<DiscoveryFilmRemoteResult> =
        flow {
            try {
                val result = service.get(genreId = genreId, originCountry = originCountry)
                emit(
                    RepositoryResult.Success(result.results.map { it.toRemoteResult() })
                )
            } catch (e: Exception) {
                val exception = repositoryExceptionMapper(e, " GetDiscoveryFilmRemoteRepository ")
                emit(RepositoryResult.Failure(exception))
            }
        }.flowOn(Dispatchers.IO)

    private fun DiscoveryFilmResultsItem.toRemoteResult() = DiscoveryFilmRemote(
        title = this.title,
        image = this.posterPath,
        movieId = this.id,
        genres = this.genreIds,
        releaseDate = this.releaseDate
    )
}