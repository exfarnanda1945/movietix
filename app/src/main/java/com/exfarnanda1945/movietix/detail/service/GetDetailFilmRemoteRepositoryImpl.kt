package com.exfarnanda1945.movietix.detail.service

import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.repositoryExceptionMapper
import com.exfarnanda1945.movietix.detail.repository.CompanyGenreFilmDetailRemote
import com.exfarnanda1945.movietix.detail.repository.DetailFilmRemote
import com.exfarnanda1945.movietix.detail.repository.DetailFilmRemoteResult
import com.exfarnanda1945.movietix.detail.repository.GenreDetailFilmRemote
import com.exfarnanda1945.movietix.detail.repository.GetDetailFilmRemoteRepository
import com.exfarnanda1945.movietix.detail.repository.VideoItemRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDetailFilmRemoteRepositoryImpl(private val service: GetDetailFilmService) :
    GetDetailFilmRemoteRepository {
    override fun get(id: Int): Flow<DetailFilmRemoteResult> = flow {
        try {
            val result = service.get(id)
            emit(RepositoryResult.Success(result.toRemoteResult()))

        } catch (e: Exception) {
            val exception = repositoryExceptionMapper(
                exception = e,
                tagException = "GetDetailFilmRemoteRepository"
            )
            emit(RepositoryResult.Failure(exception))
        }
    }

    private fun DetailFilmResponse.toRemoteResult() = DetailFilmRemote(
        image = this.posterPath ?: "",
        movieId = this.id ?: 0,
        genres = this.genres?.map { item ->
            GenreDetailFilmRemote(
                id = item?.id ?: 0,
                name = item?.name ?: ""
            )
        } ?: emptyList(),
        releaseDate = this.releaseDate ?: "",
        rate = this.voteAverage ?: 0.0,
        status = this.status ?: "",
        videos = this.videos?.results?.map { item ->
            VideoItemRemote(
                name = item?.name ?: "",
                key = item?.key ?: "",
                site = item?.site ?: "",
                type = item?.type ?: ""
            )
        } ?: emptyList(),
        posters = this.images?.posters?.map { item -> item?.filePath ?: "" } ?: emptyList(),
        collectionId = 1,
        companies = this.productionCompanies?.map { item ->
            CompanyGenreFilmDetailRemote(
                id = item?.id ?: 0,
                name = item?.name ?: "",
                logo = item?.logoPath ?: ""
            )
        } ?: emptyList(),
        description = this.overview ?: "",
        backdropImages = this.images?.backdrops?.map { item -> item?.filePath ?: "" }
            ?: emptyList(),
        title = this.title ?: ""
    )
}


