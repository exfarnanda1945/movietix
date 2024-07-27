package com.exfarnanda1945.movietix.home.banner.service

import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.repositoryExceptionMapper
import com.exfarnanda1945.movietix.home.banner.repository.BannerRemote
import com.exfarnanda1945.movietix.home.banner.repository.BannerRemoteResult
import com.exfarnanda1945.movietix.home.banner.repository.GetBannerRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBannerRemoteRepositoryImpl(private val service: GetBannerService) :
    GetBannerRemoteRepository {
    override fun get(): Flow<BannerRemoteResult> = flow {
        try {
            val result = service.getBanner()
            emit(RepositoryResult.Success(result.bannerResultItemResponses.map { it.toRemoteResult() }))
        } catch (e: Exception) {
            val exception = repositoryExceptionMapper(
                exception = e,
                tagException = "GetBannerRemoteRepository"
            )
            emit(RepositoryResult.Failure(exception))
        }
    }

    private fun BannerResultItemResponse.toRemoteResult(): BannerRemote = BannerRemote(
        title = this.title,
        image = this.posterPath,
        movieId = this.id
    )
}


