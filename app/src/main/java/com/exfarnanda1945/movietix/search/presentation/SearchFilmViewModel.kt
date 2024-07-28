package com.exfarnanda1945.movietix.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.movietix.core.domain.BadRequestError
import com.exfarnanda1945.movietix.core.domain.ConnectivityError
import com.exfarnanda1945.movietix.core.domain.DomainResult
import com.exfarnanda1945.movietix.core.domain.InternalServerError
import com.exfarnanda1945.movietix.core.domain.NotFoundError
import com.exfarnanda1945.movietix.core.domain.UnExpectedError
import com.exfarnanda1945.movietix.core.domain.UnauthorizedError
import com.exfarnanda1945.movietix.search.domain.SearchFilm
import com.exfarnanda1945.movietix.search.domain.SearchFilmUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchFilmViewModel(private val useCase: SearchFilmUseCase) : ViewModel() {
    private var _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private var _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private var _listFilm = MutableStateFlow(SearchResultState())

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val listFilm = _query
        .debounce(500)
        .onEach {
            _isSearching.value = true
        }
        .distinctUntilChanged()
        .flatMapLatest { text ->
            if (text.isEmpty()) {
                return@flatMapLatest flow {
                    emit(SearchResultState(errorMsg = "Please type in search box..."))
                }
            }
            delay(500)
            flow {
                useCase.get(text).collect { result ->
                    when (result) {
                        is DomainResult.Error -> {
                            val errorMsg = when (result.exception) {
                                is UnauthorizedError -> "Need Api Key"
                                is ConnectivityError -> "No Internet Connection"
                                is BadRequestError -> "Bad Request"
                                is NotFoundError -> "404 Not Found"
                                is InternalServerError -> "Server Error"
                                is UnExpectedError -> "Unexpected Error"
                                else -> "Error"
                            }

                            emit(SearchResultState(errorMsg = errorMsg))
                        }

                        is DomainResult.Success -> {
                            if (result.data.isEmpty()) {
                                emit(SearchResultState(errorMsg = "$text is not found!"))
                            } else {
                                emit(SearchResultState(data = result.data, errorMsg = null))
                            }
                        }
                    }
                }
            }
        }
        .onEach {
            _isSearching.update { false }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _listFilm.value
        )

    fun onEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.OnUserClearTextField -> _query.value = ""

            is SearchEvent.OnUserTyping -> _query.value = event.query
        }
    }
}

data class SearchResultState(
    val data: List<SearchFilm> = emptyList(),
    val errorMsg: String? = null
)

sealed class SearchEvent {
    data class OnUserTyping(val query: String) : SearchEvent()
    data object OnUserClearTextField : SearchEvent()
}