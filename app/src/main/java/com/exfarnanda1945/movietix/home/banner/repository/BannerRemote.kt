package com.exfarnanda1945.movietix.home.banner.repository

import java.lang.Exception

data class BannerRemote(
    val movieId: Int,
    val title: String,
    val image: String
)

sealed class BannerRemoteResult {
    data class Success(val banner: List<BannerRemote>) : BannerRemoteResult()
    data class Failure(val exception: Exception) : BannerRemoteResult()
}