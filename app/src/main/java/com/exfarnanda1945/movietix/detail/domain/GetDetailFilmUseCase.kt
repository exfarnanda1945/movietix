package com.exfarnanda1945.movietix.detail.domain

import kotlinx.coroutines.flow.Flow

interface GetDetailFilmUseCase {
    fun get(id:Int):Flow<DetailFilmResult>
}