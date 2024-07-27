package com.exfarnanda1945.movietix.home.top_genre.repository

import com.exfarnanda1945.movietix.home.banner.domain.BadRequestError
import com.exfarnanda1945.movietix.home.banner.domain.ConnectivityError
import com.exfarnanda1945.movietix.home.banner.domain.InternalServerError
import com.exfarnanda1945.movietix.home.banner.domain.NotFoundError
import com.exfarnanda1945.movietix.home.banner.domain.UnExpectedError
import com.exfarnanda1945.movietix.home.banner.domain.UnauthorizedError
import com.exfarnanda1945.movietix.home.banner.repository.BadRequestException
import com.exfarnanda1945.movietix.home.banner.repository.ConnectivityErrorException
import com.exfarnanda1945.movietix.home.banner.repository.InternalServerException
import com.exfarnanda1945.movietix.home.banner.repository.NotFoundException
import com.exfarnanda1945.movietix.home.banner.repository.UnExpectedErrorException
import com.exfarnanda1945.movietix.home.banner.repository.UnauthorizedException
import com.exfarnanda1945.movietix.home.top_genre.domain.Genre
import com.exfarnanda1945.movietix.home.top_genre.domain.GenreResult
import com.exfarnanda1945.movietix.home.top_genre.domain.GetGenresUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGenreUseCaseImpl(private val remote: GetGenreRemoteRepository) : GetGenresUseCase {
    override fun get(): Flow<GenreResult> = flow {
        remote.get().collect { result ->
            when (result) {
                is GenreRemoteResult.Failure -> when (result.exception) {
                    is UnauthorizedException -> emit(GenreResult.Failure(UnauthorizedError()))
                    is ConnectivityErrorException -> emit(GenreResult.Failure(ConnectivityError()))
                    is BadRequestException -> emit(GenreResult.Failure(BadRequestError()))
                    is NotFoundException -> emit(GenreResult.Failure(NotFoundError()))
                    is InternalServerException -> emit(GenreResult.Failure(InternalServerError()))
                    is UnExpectedErrorException -> emit(GenreResult.Failure(UnExpectedError()))
                }

                is GenreRemoteResult.Success -> {
                    val mapToResult = result.genres.map { it.toResult() }
                    emit(GenreResult.Success(mapToResult))
                }
            }
        }
    }

    private fun GenreRemote.toResult(): Genre = Genre(
        id = this.id,
        name = this.name
    )
}
