package com.exfarnanda1945.movietix.home.banner.domain

import java.lang.Exception

data class Banner(
    val movieId: Int,
    val title: String,
    val image: String
)

sealed class BannerResult {
    data class Success(val banner: List<Banner>) : BannerResult()
    data class Failure(val exception: Exception) : BannerResult()
}

class UnauthorizedError : Exception()
class BadRequestError : Exception()
class InternalServerError : Exception()
class NotFoundError : Exception()
class ConnectivityError : Exception()
class UnExpectedError : Exception()