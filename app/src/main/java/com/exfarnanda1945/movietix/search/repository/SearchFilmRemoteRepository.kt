package com.exfarnanda1945.movietix.search.repository

import kotlinx.coroutines.flow.Flow

interface SearchFilmRemoteRepository {
    fun get(query:String): Flow<SearchFilmRemoteResult>
}