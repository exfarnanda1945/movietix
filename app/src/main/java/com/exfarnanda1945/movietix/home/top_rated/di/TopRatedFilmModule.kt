package com.exfarnanda1945.movietix.home.top_rated.di

import com.exfarnanda1945.movietix.core.di.HttpFactories
import com.exfarnanda1945.movietix.home.top_rated.domain.GetTopRatedFilmUseCase
import com.exfarnanda1945.movietix.home.top_rated.presentation.TopRatedFilmViewModel
import com.exfarnanda1945.movietix.home.top_rated.repository.GetTopRatedFilmRemoteRepository
import com.exfarnanda1945.movietix.home.top_rated.repository.GetTopRatedFilmUseCaseImpl
import com.exfarnanda1945.movietix.home.top_rated.service.GetTopRatedFilmRemoteRepositoryImpl
import com.exfarnanda1945.movietix.home.top_rated.service.GetTopRatedFilmService
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val topRatedFilmModule = module {
    factory { HttpFactories.createRemoteService(get(), GetTopRatedFilmService::class.java) }
    factoryOf(::GetTopRatedFilmRemoteRepositoryImpl) { bind<GetTopRatedFilmRemoteRepository>() }
    factoryOf(::GetTopRatedFilmUseCaseImpl) { bind<GetTopRatedFilmUseCase>() }
    viewModelOf(::TopRatedFilmViewModel)
}