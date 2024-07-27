package com.exfarnanda1945.movietix.home.banner.domain

import com.exfarnanda1945.movietix.core.domain.DomainResult
import java.lang.Exception

data class Banner(
    val movieId: Int,
    val title: String,
    val image: String
)


typealias BannerResult = DomainResult<List<Banner>, Exception>
