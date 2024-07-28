package com.exfarnanda1945.movietix.detail.repository

import com.exfarnanda1945.movietix.core.repository.RepositoryResult

data class DetailFilmRemote(
    val title:String,
    val movieId: Int,
    val image: String,
    val collectionId: Int,
    val genres: List<GenreDetailFilmRemote>,
    val description: String,
    val companies: List<CompanyGenreFilmDetailRemote>,
    val releaseDate: String,
    val status: String,
    val rate: Double,
    val backdropImages: List<String> = listOf(),
    val posters: List<String> = listOf(),
    val videos: List<VideoItemRemote> = listOf()
)

class CompanyGenreFilmDetailRemote(
    val id: Int,
    val logo: String,
    val name: String
)

data class GenreDetailFilmRemote(
    val id: Int,
    val name: String
)

data class VideoItemRemote(
    val name: String,
    val site: String,
    val key: String,
    val type: String
)

typealias DetailFilmRemoteResult = RepositoryResult<DetailFilmRemote, Exception>
