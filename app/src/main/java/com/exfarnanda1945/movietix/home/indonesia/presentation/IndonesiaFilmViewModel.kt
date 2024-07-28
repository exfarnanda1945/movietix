package com.exfarnanda1945.movietix.home.indonesia.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.movietix.core.domain.BadRequestError
import com.exfarnanda1945.movietix.core.domain.ConnectivityError
import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.InternalServerError
import com.exfarnanda1945.movietix.core.domain.NotFoundError
import com.exfarnanda1945.movietix.core.domain.UnExpectedError
import com.exfarnanda1945.movietix.core.domain.UnauthorizedError
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.DiscoveryFilm
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.GetDiscoveryFilmUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IndonesiaFilmViewModel(private val useCase: GetDiscoveryFilmUseCase) : ViewModel() {
    private var _filmState = MutableStateFlow(FilmState())
    val filmState = _filmState.asStateFlow()


    fun load(genreId: Int? = null, originCountry: String? = null) = viewModelScope.launch {
        _filmState.value = filmState.value.copy(
            isLoading = true
        )

        useCase.get(
            genreId = genreId,
            originCountry = originCountry
        ).collect { result ->
            delay(1000)
            when (result) {
                is DomainResult.Error -> {
                    _filmState.value = filmState.value.copy(
                        isLoading = false,
                        errorMsg = when (result.exception) {
                            is UnauthorizedError -> "Need Api Key"
                            is ConnectivityError -> "No Internet Connection"
                            is BadRequestError -> "Bad Request"
                            is NotFoundError -> "404 Not Found"
                            is InternalServerError -> "Server Error"
                            is UnExpectedError -> "Unexpected Error"
                            else -> "Error"
                        },
                        data = emptyList()
                    )
                }

                is DomainResult.Success -> {
                    _filmState.value = filmState.value.copy(
                        isLoading = false,
                        data = result.data.take(4),
                        errorMsg = null
                    )
                }
            }
        }
    }
}

data class FilmState(
    val isLoading: Boolean = false,
    val data: List<DiscoveryFilm> = listOf(),
    val errorMsg: String? = null
)