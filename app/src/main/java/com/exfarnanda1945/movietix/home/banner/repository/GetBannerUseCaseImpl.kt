package com.exfarnanda1945.movietix.home.banner.repository

import com.exfarnanda1945.movietix.home.banner.domain.BadRequestError
import com.exfarnanda1945.movietix.home.banner.domain.Banner
import com.exfarnanda1945.movietix.home.banner.domain.BannerResult
import com.exfarnanda1945.movietix.home.banner.domain.ConnectivityError
import com.exfarnanda1945.movietix.home.banner.domain.GetBannerUseCase
import com.exfarnanda1945.movietix.home.banner.domain.InternalServerError
import com.exfarnanda1945.movietix.home.banner.domain.NotFoundError
import com.exfarnanda1945.movietix.home.banner.domain.UnExpectedError
import com.exfarnanda1945.movietix.home.banner.domain.UnauthorizedError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBannerUseCaseImpl(private val remote: GetBannerRemoteRepository) : GetBannerUseCase {
    override fun get(): Flow<BannerResult> = flow {
        remote.get().collect { result ->
            when (result) {
                is BannerRemoteResult.Failure -> {
                    when (result.exception) {
                        is UnauthorizedException -> emit(BannerResult.Failure(UnauthorizedError()))
                        is ConnectivityErrorException -> emit(BannerResult.Failure(ConnectivityError()))
                        is BadRequestException -> emit(BannerResult.Failure(BadRequestError()))
                        is NotFoundException -> emit(BannerResult.Failure(NotFoundError()))
                        is InternalServerException -> emit(BannerResult.Failure(InternalServerError()))
                        is UnExpectedErrorException -> emit(BannerResult.Failure(UnExpectedError()))
                    }
                }

                is BannerRemoteResult.Success -> emit(
                    BannerResult.Success(
                        result.banner.map { it.toResult() })
                )
            }
        }
    }

    private fun BannerRemote.toResult(): Banner = Banner(
        image = this.image,
        movieId = this.movieId,
        title = this.title
    )

}