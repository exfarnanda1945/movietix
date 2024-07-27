package com.exfarnanda1945.movietix.home.top_genre.di

import com.exfarnanda1945.movietix.core.di.HttpFactories
import com.exfarnanda1945.movietix.home.top_genre.domain.GetGenresUseCase
import com.exfarnanda1945.movietix.home.top_genre.presentation.TopGenreScreenViewModel
import com.exfarnanda1945.movietix.home.top_genre.repository.GetGenreRemoteRepository
import com.exfarnanda1945.movietix.home.top_genre.repository.GetGenreUseCaseImpl
import com.exfarnanda1945.movietix.home.top_genre.service.GetGenreRemoteRepositoryImpl
import com.exfarnanda1945.movietix.home.top_genre.service.GetGenreService
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val topGenreModule = module {
    factory { HttpFactories.createRemoteService(get(), GetGenreService::class.java) }
    factoryOf(::GetGenreRemoteRepositoryImpl) { bind<GetGenreRemoteRepository>() }
    factoryOf(::GetGenreUseCaseImpl) { bind<GetGenresUseCase>() }
   viewModelOf(::TopGenreScreenViewModel)
}