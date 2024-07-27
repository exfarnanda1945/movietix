package com.exfarnanda1945.movietix.core.domain

import com.exfarnanda1945.movietix.core.repository.BadRequestException
import com.exfarnanda1945.movietix.core.repository.ConnectivityErrorException
import com.exfarnanda1945.movietix.core.repository.InternalServerException
import com.exfarnanda1945.movietix.core.repository.NotFoundException
import com.exfarnanda1945.movietix.core.repository.UnExpectedErrorException
import com.exfarnanda1945.movietix.core.repository.UnauthorizedException
import java.lang.Exception


class UnauthorizedError : Exception()
class BadRequestError : Exception()
class InternalServerError : Exception()
class NotFoundError : Exception()
class ConnectivityError : Exception()
class UnExpectedError : Exception()

fun domainExceptionMapping(exception: Exception) = when (exception) {
    is UnauthorizedException -> UnauthorizedError()
    is ConnectivityErrorException -> ConnectivityError()
    is BadRequestException -> BadRequestError()
    is NotFoundException -> NotFoundError()
    is InternalServerException -> InternalServerError()
    is UnExpectedErrorException -> UnExpectedError()
    else -> UnExpectedError()
}