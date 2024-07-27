package com.exfarnanda1945.movietix.home.top_rated.service

import com.exfarnanda1945.movietix.home.shared_discovery_film.service.DiscoveryFilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetTopRatedFilmService {
    @GET("movie/top_rated")
    fun get(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): TopRatedFilmResponse
}