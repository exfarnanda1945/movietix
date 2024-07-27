package com.exfarnanda1945.movietix.home.banner.remote.models

import com.squareup.moshi.Json

data class BannerResponseDates(
    @Json(name = "maximum") val maximum: String,
    @Json(name = "minimum") val minimum: String
)