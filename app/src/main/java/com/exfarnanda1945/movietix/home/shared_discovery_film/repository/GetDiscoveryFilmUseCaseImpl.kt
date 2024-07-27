package com.exfarnanda1945.movietix.home.shared_discovery_film.repository

import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.domainExceptionMapper
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.DiscoveryFilm
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.DiscoveryFilmResult
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.GetDiscoveryFilmUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDiscoveryFilmUseCaseImpl(private val remote: GetDiscoveryFilmRemoteRepository) :
    GetDiscoveryFilmUseCase {
    override fun get(genreId: Int?, originCountry: String?): Flow<DiscoveryFilmResult> = flow {
        remote.get(genreId = genreId, originCountry = originCountry).collect { result ->
            when (result) {
                is RepositoryResult.Failure -> {
                    val exception = domainExceptionMapper(result.exception)
                    emit(DomainResult.Error(exception))
                }

                is RepositoryResult.Success -> emit(DomainResult.Success(
                    result.data.map { it.toResult() }
                ))
            }
        }
    }

    private fun DiscoveryFilmRemote.toResult() = DiscoveryFilm(
        title = this.title,
        image = this.image,
        movieId = this.movieId,
        genres = this.genres,
        releaseDate = this.releaseDate
    )
}


