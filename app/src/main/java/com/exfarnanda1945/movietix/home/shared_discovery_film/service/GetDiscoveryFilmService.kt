package com.exfarnanda1945.movietix.home.shared_discovery_film.service

import retrofit2.http.GET
import retrofit2.http.Query

interface GetDiscoveryFilmService {
    @GET("discover/movie/")
    fun get(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int? = null,
        @Query("with_origin_country") originCountry:String? = null
    ): DiscoveryFilmResponse
}