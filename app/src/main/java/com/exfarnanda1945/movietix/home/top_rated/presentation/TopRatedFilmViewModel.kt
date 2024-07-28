package com.exfarnanda1945.movietix.home.top_rated.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.movietix.core.domain.BadRequestError
import com.exfarnanda1945.movietix.core.domain.ConnectivityError
import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.InternalServerError
import com.exfarnanda1945.movietix.core.domain.NotFoundError
import com.exfarnanda1945.movietix.core.domain.UnExpectedError
import com.exfarnanda1945.movietix.core.domain.UnauthorizedError
import com.exfarnanda1945.movietix.home.top_rated.domain.GetTopRatedFilmUseCase
import com.exfarnanda1945.movietix.home.top_rated.domain.TopRatedFilm
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopRatedFilmViewModel(
    private val useCase: GetTopRatedFilmUseCase,
) : ViewModel() {
        private var _topRatedState = MutableStateFlow(BannerUiState())
            val topRatedState = _topRatedState.asStateFlow()

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
                _topRatedState.value = topRatedState.value.copy(
            isLoading = true
        )

        useCase.get().collect { result ->
            delay(1000)
            when (result) {
                is DomainResult.Error -> {
                            _topRatedState.value = topRatedState.value.copy(
                        isLoading = false,
                        errorMsg = when (result.exception) {
                            is UnauthorizedError -> "Need Api Key"
                            is ConnectivityError -> "No Internet Connection"
                            is BadRequestError -> "Bad Request"
                            is NotFoundError -> "404 Not Found"
                            is InternalServerError -> "Server Error"
                            is UnExpectedError -> "Unexpected Error"
                            else -> "Error"
                        }
                    )
                }

                is DomainResult.Success -> {
                            _topRatedState.value = topRatedState.value.copy(
                        isLoading = false,
                        data = result.data.take(8)
                    )
                }
            }
        }
    }
}

data class BannerUiState(
    val isLoading: Boolean = false,
    val data: List<TopRatedFilm> = listOf(),
    val errorMsg: String? = null
)