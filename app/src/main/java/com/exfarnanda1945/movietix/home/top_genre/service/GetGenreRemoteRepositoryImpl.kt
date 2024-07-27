package com.exfarnanda1945.movietix.home.top_genre.service

import android.util.Log
import com.exfarnanda1945.movietix.home.banner.repository.BadRequestException
import com.exfarnanda1945.movietix.home.banner.repository.ConnectivityErrorException
import com.exfarnanda1945.movietix.home.banner.repository.InternalServerException
import com.exfarnanda1945.movietix.home.banner.repository.NotFoundException
import com.exfarnanda1945.movietix.home.banner.repository.UnExpectedErrorException
import com.exfarnanda1945.movietix.home.banner.repository.UnauthorizedException
import com.exfarnanda1945.movietix.home.top_genre.repository.GenreRemote
import com.exfarnanda1945.movietix.home.top_genre.repository.GenreRemoteResult
import com.exfarnanda1945.movietix.home.top_genre.repository.GetGenreRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetGenreRemoteRepositoryImpl(private val service: GetGenreService) : GetGenreRemoteRepository {
    override fun get(): Flow<GenreRemoteResult> = flow {
        try {
            val result = service.get()
            emit(GenreRemoteResult.Success(
                result.genres.map { it.toRemoteResult() }
            ))

        } catch (e: Exception) {
            when (e) {
                is IOException -> emit(GenreRemoteResult.Failure(ConnectivityErrorException()))
                is HttpException -> {
                    when (e.code()) {
                        401 -> emit(GenreRemoteResult.Failure(UnauthorizedException()))
                        400 -> emit(GenreRemoteResult.Failure(BadRequestException()))
                        404 -> emit(GenreRemoteResult.Failure(NotFoundException()))
                        500 -> emit(GenreRemoteResult.Failure(InternalServerException()))
                    }
                }

                else -> {
                    e.printStackTrace()
                    Log.e("getGenreServiceError", "get: ${e.message}")
                    emit(GenreRemoteResult.Failure(UnExpectedErrorException()))
                }
            }
        }
    }


    private fun GenresItem.toRemoteResult(): GenreRemote = GenreRemote(
        id = this.id,
        name = this.name
    )
}

