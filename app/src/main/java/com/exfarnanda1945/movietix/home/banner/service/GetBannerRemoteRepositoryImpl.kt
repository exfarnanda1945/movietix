package com.exfarnanda1945.movietix.home.banner.service

import android.util.Log
import com.exfarnanda1945.movietix.core.repository.BadRequestException
import com.exfarnanda1945.movietix.core.repository.ConnectivityErrorException
import com.exfarnanda1945.movietix.core.repository.InternalServerException
import com.exfarnanda1945.movietix.core.repository.NotFoundException
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.UnExpectedErrorException
import com.exfarnanda1945.movietix.core.repository.UnauthorizedException
import com.exfarnanda1945.movietix.home.banner.repository.BannerRemote
import com.exfarnanda1945.movietix.home.banner.repository.BannerRemoteResult
import com.exfarnanda1945.movietix.home.banner.repository.GetBannerRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetBannerRemoteRepositoryImpl(private val service: GetBannerService) :
    GetBannerRemoteRepository {
    override fun get(): Flow<BannerRemoteResult> = flow {
        try {
            val result = service.getBanner()
            emit(RepositoryResult.Success(result.bannerResultItemResponses.map { it.toRemoteResult() }))
        } catch (e: Exception) {
            when (e) {
                is IOException -> emit(RepositoryResult.Exception(ConnectivityErrorException()))
                is HttpException -> {
                    when (e.code()) {
                        401 -> emit(RepositoryResult.Exception(UnauthorizedException()))
                        400 -> emit(RepositoryResult.Exception(BadRequestException()))
                        404 -> emit(RepositoryResult.Exception(NotFoundException()))
                        500 -> emit(RepositoryResult.Exception(InternalServerException()))
                    }
                }

                else -> {
                    e.printStackTrace()
                    Log.e("getBannerServiceError", "get: ${e.message}")
                    emit(RepositoryResult.Exception(UnExpectedErrorException()))
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


