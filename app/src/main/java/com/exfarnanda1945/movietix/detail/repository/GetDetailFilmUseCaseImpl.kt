package com.exfarnanda1945.movietix.detail.repository

import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.domainExceptionMapper
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.detail.domain.CompanyFilmDetail
import com.exfarnanda1945.movietix.detail.domain.DetailFilm
import com.exfarnanda1945.movietix.detail.domain.DetailFilmResult
import com.exfarnanda1945.movietix.detail.domain.GenreDetailFilm
import com.exfarnanda1945.movietix.detail.domain.GetDetailFilmUseCase
import com.exfarnanda1945.movietix.detail.domain.VideoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDetailFilmUseCaseImpl(private val remote: GetDetailFilmRemoteRepository) :
    GetDetailFilmUseCase {
    override fun get(id: Int): Flow<DetailFilmResult> = flow {
        remote.get(id).collect { result ->
            when (result) {
                is RepositoryResult.Failure -> {
                    val exception = domainExceptionMapper(result.exception)
                    emit(DomainResult.Error(exception))
                }

                is RepositoryResult.Success -> {
                    val mapToModel: DetailFilm = result.data.toResult()
                    emit(DomainResult.Success(mapToModel))
                }
            }
        }
    }
}

private fun DetailFilmRemote.toResult(): DetailFilm = DetailFilm(
    movieId = this.movieId,
    image = this.image,
    description = this.description,
    rate = this.rate,
    backdropImages = this.backdropImages.map { it },
    posters = this.posters.map { it },
    collectionId = this.collectionId,
    releaseDate = this.releaseDate,
    status = this.status,
    videos = this.videos.map { item ->
        VideoItem(
            name = item.name,
            key = item.key,
            site = item.site,
            type = item.type
        )
    },
    companies = this.companies.map { item ->
        CompanyFilmDetail(
            id = item.id,
            name = item.name,
            logo = item.logo
        )
    },
    genres = this.genres.map { item ->
        GenreDetailFilm(
            id = item.id,
            name = item.name
        )
    },
    title = this.title
)

