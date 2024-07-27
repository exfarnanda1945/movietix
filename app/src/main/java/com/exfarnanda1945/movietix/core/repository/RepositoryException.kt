package com.exfarnanda1945.movietix.core.repository

import android.util.Log
import retrofit2.HttpException
import java.io.IOException

class UnauthorizedException : Exception()
class BadRequestException : Exception()
class InternalServerException : Exception()
class NotFoundException : Exception()
class ConnectivityErrorException : Exception()
class UnExpectedErrorException : Exception()

fun repositoryExceptionMapper(exception: Exception,tagException:String) = when (exception) {
    is IOException -> ConnectivityErrorException()
    is HttpException -> {
        when (exception.code()) {
            401 -> UnauthorizedException()
            400 -> BadRequestException()
            404 -> NotFoundException()
            500 -> InternalServerException()
            else -> UnExpectedErrorException()
        }
    }
    else -> {
        exception.printStackTrace()
        Log.e(tagException, "exception: ${exception.message}")
        UnExpectedErrorException()
    }
}