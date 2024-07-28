package com.exfarnanda1945.movietix.detail.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetDetailFilmService {
    @GET("movie/{movie_id}")
    suspend fun get(
        @Path("movie_id") id: Int,
        @Query("language") language: String = "en-US",
        @Query("include_image_language") includeImageLanguage: String = "null",
        @Query("append_to_response") appendToResponse: String = "videos,images"
    ): DetailFilmResponse
}