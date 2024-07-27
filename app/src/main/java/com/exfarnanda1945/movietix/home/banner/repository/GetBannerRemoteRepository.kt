package com.exfarnanda1945.movietix.home.banner.repository

import kotlinx.coroutines.flow.Flow

interface GetBannerRemoteRepository {
    fun get(): Flow<BannerRemoteResult>
}

class UnauthorizedException:Exception()
class BadRequestException:Exception()
class InternalServerException:Exception()
class NotFoundException:Exception()
class ConnectivityErrorException:Exception()
class UnExpectedErrorException:Exception()


