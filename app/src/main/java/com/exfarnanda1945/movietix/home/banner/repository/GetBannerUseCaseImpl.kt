package com.exfarnanda1945.movietix.home.banner.repository

import com.exfarnanda1945.movietix.core.domain.BadRequestError
import com.exfarnanda1945.movietix.core.domain.ConnectivityError
import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.InternalServerError
import com.exfarnanda1945.movietix.core.domain.NotFoundError
import com.exfarnanda1945.movietix.core.domain.UnExpectedError
import com.exfarnanda1945.movietix.core.domain.UnauthorizedError
import com.exfarnanda1945.movietix.core.repository.BadRequestException
import com.exfarnanda1945.movietix.core.repository.ConnectivityErrorException
import com.exfarnanda1945.movietix.core.repository.InternalServerException
import com.exfarnanda1945.movietix.core.repository.NotFoundException
import com.exfarnanda1945.movietix.core.repository.RepositoryResult
import com.exfarnanda1945.movietix.core.repository.UnExpectedErrorException
import com.exfarnanda1945.movietix.core.repository.UnauthorizedException
import com.exfarnanda1945.movietix.home.banner.domain.Banner
import com.exfarnanda1945.movietix.home.banner.domain.BannerResult
import com.exfarnanda1945.movietix.home.banner.domain.GetBannerUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBannerUseCaseImpl(private val remote: GetBannerRemoteRepository) : GetBannerUseCase {
    override fun get(): Flow<BannerResult> = flow {
        remote.get().collect { result ->
            when (result) {
                is RepositoryResult.Exception -> {
                    when (result.exception) {
                        is UnauthorizedException -> emit(DomainResult.Error(UnauthorizedError()))
                        is ConnectivityErrorException -> emit(DomainResult.Error(ConnectivityError()))
                        is BadRequestException -> emit(DomainResult.Error(BadRequestError()))
                        is NotFoundException -> emit(DomainResult.Error(NotFoundError()))
                        is InternalServerException -> emit(DomainResult.Error(InternalServerError()))
                        is UnExpectedErrorException -> emit(DomainResult.Error(UnExpectedError()))
                    }
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