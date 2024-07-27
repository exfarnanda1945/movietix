package com.exfarnanda1945.movietix.home.banner.service

import android.util.Log
import com.exfarnanda1945.movietix.home.banner.repository.BadRequestException
import com.exfarnanda1945.movietix.home.banner.repository.BannerRemote
import com.exfarnanda1945.movietix.home.banner.repository.BannerRemoteResult
import com.exfarnanda1945.movietix.home.banner.repository.ConnectivityErrorException
import com.exfarnanda1945.movietix.home.banner.repository.GetBannerRemoteRepository
import com.exfarnanda1945.movietix.home.banner.repository.InternalServerException
import com.exfarnanda1945.movietix.home.banner.repository.NotFoundException
import com.exfarnanda1945.movietix.home.banner.repository.UnExpectedErrorException
import com.exfarnanda1945.movietix.home.banner.repository.UnauthorizedException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetBannerRemoteRepositoryImpl(private val service: GetBannerService) :
    GetBannerRemoteRepository {
    override fun get(): Flow<BannerRemoteResult> = flow {
        try {
            val result = service.getBanner()
            emit(BannerRemoteResult.Success(result.bannerResultItemResponses.map { it.toRemoteResult() }))
        } catch (e: Exception) {
            when (e) {
                is IOException -> emit(BannerRemoteResult.Failure(ConnectivityErrorException()))
                is HttpException -> {
                    when (e.code()) {
                        401 -> emit(BannerRemoteResult.Failure(UnauthorizedException()))
                        400 -> emit(BannerRemoteResult.Failure(BadRequestException()))
                        404 -> emit(BannerRemoteResult.Failure(NotFoundException()))
                        500 -> emit(BannerRemoteResult.Failure(InternalServerException()))
                    }
                }
                else -> {
                    e.printStackTrace()
                    Log.e("getBannerServiceError", "get: ${e.message}")
                    emit(BannerRemoteResult.Failure(UnExpectedErrorException()))
                }
            }
        }
    }

    private fun BannerResultItemResponse.toRemoteResult(): BannerRemote = BannerRemote(
        title = this.title,
        image = this.posterPath,
        movieId = this.id
    )
}


