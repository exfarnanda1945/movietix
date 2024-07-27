package com.exfarnanda1945.movietix.home.banner.repository

import com.exfarnanda1945.movietix.core.repository.RepositoryResult

data class BannerRemote(
    val movieId: Int,
    val title: String,
    val image: String
)

typealias BannerRemoteResult = RepositoryResult<List<BannerRemote>, Exception>
