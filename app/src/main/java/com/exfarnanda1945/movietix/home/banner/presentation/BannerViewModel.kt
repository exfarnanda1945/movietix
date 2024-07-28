package com.exfarnanda1945.movietix.home.banner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.movietix.core.domain.BadRequestError
import com.exfarnanda1945.movietix.core.domain.ConnectivityError
import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.InternalServerError
import com.exfarnanda1945.movietix.core.domain.NotFoundError
import com.exfarnanda1945.movietix.core.domain.UnExpectedError
import com.exfarnanda1945.movietix.core.domain.UnauthorizedError
import com.exfarnanda1945.movietix.home.banner.domain.Banner
import com.exfarnanda1945.movietix.home.banner.domain.GetBannerUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BannerViewModel(private val useCase: GetBannerUseCase) : ViewModel() {
    private var _bannerState = MutableStateFlow(BannerUiState())
    val bannerState = _bannerState.asStateFlow()

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        _bannerState.value = bannerState.value.copy(
            isLoading = true
        )

        useCase.get().collect { result ->
            delay(1000)
            when (result) {
                is DomainResult.Error -> {
                    _bannerState.value = bannerState.value.copy(
                        isLoading = false,
                        data = emptyList(),
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
                    _bannerState.value = bannerState.value.copy(
                        isLoading = false,
                        data = result.data.take(6),
                        errorMsg = null
                    )
                }
            }
        }
    }
}

data class BannerUiState(
    val isLoading: Boolean = false,
    val data: List<Banner> = listOf(),
    val errorMsg: String? = null
)