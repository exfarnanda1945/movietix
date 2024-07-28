package com.exfarnanda1945.movietix.search.domain

import com.exfarnanda1945.movietix.core.domain.DomainResult

data class SearchFilm(
    val movieId: Int,
    val title: String,
    val rate: Double,
    val image: String,
    val releaseDate: String
)

typealias SearchFilmResult = DomainResult<List<SearchFilm>,Exception>