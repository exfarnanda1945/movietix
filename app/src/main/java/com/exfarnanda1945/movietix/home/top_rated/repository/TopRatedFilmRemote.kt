package com.exfarnanda1945.movietix.home.top_rated.repository

import com.exfarnanda1945.movietix.core.repository.RepositoryResult

data class TopRatedFilmRemote(
    val movieId: Int,
    val title: String,
    val image: String,
    val rated: Double
)

typealias TopRatedFilmRemoteResult = RepositoryResult<List<TopRatedFilmRemote>, Exception>