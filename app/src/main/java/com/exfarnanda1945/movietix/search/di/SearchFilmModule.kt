package com.exfarnanda1945.movietix.search.di

import com.exfarnanda1945.movietix.core.di.HttpFactories
import com.exfarnanda1945.movietix.search.domain.SearchFilmUseCase
import com.exfarnanda1945.movietix.search.presentation.SearchFilmViewModel
import com.exfarnanda1945.movietix.search.repository.SearchFilmRemoteRepository
import com.exfarnanda1945.movietix.search.repository.SearchFilmUseCaseImpl
import com.exfarnanda1945.movietix.search.service.SearchFilmRemoteRepositoryImpl
import com.exfarnanda1945.movietix.search.service.SearchFilmService
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val searchFilmModule = module {
    factory { HttpFactories.createRemoteService(get(), SearchFilmService::class.java) }
    factoryOf(::SearchFilmRemoteRepositoryImpl) { bind<SearchFilmRemoteRepository>() }
    factoryOf(::SearchFilmUseCaseImpl) { bind<SearchFilmUseCase>() }
    viewModelOf(::SearchFilmViewModel)
}