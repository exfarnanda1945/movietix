package com.exfarnanda1945.movietix.home.banner.domain

import kotlinx.coroutines.flow.Flow


interface GetBannerUseCase {
    fun get(): Flow<BannerResult>
}