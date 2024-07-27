package com.exfarnanda1945.movietix.home.banner.repository

import kotlinx.coroutines.flow.Flow

interface GetBannerRemoteRepository {
    fun get(): Flow<BannerRemoteResult>
}




