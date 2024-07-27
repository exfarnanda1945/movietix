package com.exfarnanda1945.movietix.home.top_genre.service

import retrofit2.http.GET
import retrofit2.http.Query

interface GetGenreService {
    @GET("genre/movie/list")
    fun get(
        @Query("language") language: String = "en"
    ): GenreResponse
}