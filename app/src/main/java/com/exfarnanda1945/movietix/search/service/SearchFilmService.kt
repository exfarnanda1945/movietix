package com.exfarnanda1945.movietix.search.service

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchFilmService {
    @GET("search/movie")
    suspend fun get(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): SearchFilmResponse
}