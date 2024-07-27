package com.exfarnanda1945.movietix.home.top_rated.domain

import com.exfarnanda1945.movietix.core.domain.DomainResult

data class TopRatedFilm(
    val movieId: Int,
    val title: String,
    val image: String,
    val rated:Double
)

typealias TopRatedFilmResult = DomainResult<List<TopRatedFilm>, Exception>