package com.exfarnanda1945.movietix.detail.repository

import kotlinx.coroutines.flow.Flow

interface GetDetailFilmRemoteRepository {
    fun get(id:Int): Flow<DetailFilmRemoteResult>
}