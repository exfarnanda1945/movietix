package com.exfarnanda1945.movietix.search.domain

import kotlinx.coroutines.flow.Flow

interface SearchFilmUseCase {
    fun get(query:String): Flow<SearchFilmResult>
}