package com.exfarnanda1945.movietix.detail.domain

import com.exfarnanda1945.movietix.core.domain.DomainResult

data class DetailFilm(
    val title: String = "",
    val movieId: Int = 0,
    val image: String = "",
    val collectionId: Int = 0,
    val genres: List<GenreDetailFilm> = emptyList(),
    val description: String = "",
    val companies: List<CompanyFilmDetail> = emptyList(),
    val releaseDate: String = "",
    val status: String = "",
    val rate: Double = 0.0,
    val backdropImages: List<String> = listOf(),
    val posters: List<String> = listOf(),
    val videos: List<VideoItem> = listOf()
)

data class VideoItem(
    val name: String = "",
    val site: String = "",
    val key: String = "",
    val type: String = ""
)


class CompanyFilmDetail(
    val id: Int = 0,
    val logo: String = "",
    val name: String = ""
)

data class GenreDetailFilm(
    val id: Int = 0,
    val name: String = ""
)

typealias DetailFilmResult = DomainResult<DetailFilm, Exception>