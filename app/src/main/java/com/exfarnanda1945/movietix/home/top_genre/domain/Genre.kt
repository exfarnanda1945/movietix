package com.exfarnanda1945.movietix.home.top_genre.domain

import com.exfarnanda1945.movietix.core.domain.DomainResult
import java.lang.Exception

data class Genre(
    val id: Int,
    val name: String
)

typealias GenreResult = DomainResult<List<Genre>,Exception>
