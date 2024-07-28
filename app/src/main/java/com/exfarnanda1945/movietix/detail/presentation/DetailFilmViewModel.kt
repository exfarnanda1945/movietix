package com.exfarnanda1945.movietix.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.movietix.core.domain.BadRequestError
import com.exfarnanda1945.movietix.core.domain.ConnectivityError
import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.InternalServerError
import com.exfarnanda1945.movietix.core.domain.NotFoundError
import com.exfarnanda1945.movietix.core.domain.UnExpectedError
import com.exfarnanda1945.movietix.core.domain.UnauthorizedError
import com.exfarnanda1945.movietix.detail.domain.DetailFilm
import com.exfarnanda1945.movietix.detail.domain.GetDetailFilmUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailFilmViewModel(private val useCase: GetDetailFilmUseCase) : ViewModel() {
    private var _detailState = MutableStateFlow(DetailFilmState())
    val detailState = _detailState.asStateFlow()

    fun load(id: Int) = viewModelScope.launch {

        useCase.get(id).collect { result ->
            delay(1000)
            when (result) {
                is DomainResult.Error -> _detailState.value = detailState.value.copy(
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

                is DomainResult.Success -> {
                    _detailState.value = detailState.value.copy(
                        isLoading = false,
                        data = result.data

                    )
                }
            }
        }
    }

}

data class DetailFilmState(
    val isLoading: Boolean = true,
    val data: DetailFilm = DetailFilm(),
    val errorMsg: String? = null
)