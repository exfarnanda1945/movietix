package com.exfarnanda1945.movietix.home.banner.service

import retrofit2.http.GET
import retrofit2.http.Query

interface GetBannerService {
    @GET("movie/now_playing")
    suspend fun getBanner(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): BannerResponse
}