package com.exfarnanda1945.movietix.home.banner.remote.models

import com.squareup.moshi.Json

data class BannerResponse(
    @Json(name = "dates") val dates: BannerResponseDates,
    @Json(name = "page") val page: Int,
    @Json(name = "results") val bannerResultItemResponses: List<BannerResultItemResponse>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)