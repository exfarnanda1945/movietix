package com.exfarnanda1945.movietix.home.banner.repository

import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.domainExceptionMapper
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.home.banner.domain.Banner
import com.exfarnanda1945.movietix.home.banner.domain.BannerResult
import com.exfarnanda1945.movietix.home.banner.domain.GetBannerUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBannerUseCaseImpl(private val remote: GetBannerRemoteRepository) : GetBannerUseCase {
    override fun get(): Flow<BannerResult> = flow {
        remote.get().collect { result ->
            when (result) {
                is RepositoryResult.Failure -> {
                    val exception = domainExceptionMapper(exception = result.exception)
                    emit(DomainResult.Error(exception))
                }

                is RepositoryResult.Success -> {
                    val mapToResult = result.data.map { it.toResult() }
                    emit(DomainResult.Success(mapToResult))
                }
            }
        }
    }

    private fun BannerRemote.toResult(): Banner = Banner(
        image = this.image,
        movieId = this.movieId,
        title = this.title
    )

}