package com.exfarnanda1945.movietix.core.domain

sealed class DomainResult<out T, out E> {
    data class Success<T>(val data: T) : DomainResult<T, Nothing>()
    data class Error<E>(val exception: E) : DomainResult<Nothing, E>()
}