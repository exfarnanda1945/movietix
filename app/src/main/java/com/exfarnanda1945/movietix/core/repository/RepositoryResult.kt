package com.exfarnanda1945.movietix.core.repository

sealed class RepositoryResult<out T, out E> {
    data class Success<T>(val data: T) : RepositoryResult<T, Nothing>()
    data class Exception<E>(val exception: E) : RepositoryResult<Nothing, E>()
}