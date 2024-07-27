package com.exfarnanda1945.movietix.core.domain

import java.lang.Exception


class UnauthorizedError : Exception()
class BadRequestError : Exception()
class InternalServerError : Exception()
class NotFoundError : Exception()
class ConnectivityError : Exception()
class UnExpectedError : Exception()