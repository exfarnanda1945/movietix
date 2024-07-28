package com.exfarnanda1945.movietix.detail.di

import com.exfarnanda1945.movietix.core.di.HttpFactories
import com.exfarnanda1945.movietix.detail.domain.GetDetailFilmUseCase
import com.exfarnanda1945.movietix.detail.presentation.DetailFilmViewModel
import com.exfarnanda1945.movietix.detail.repository.GetDetailFilmRemoteRepository
import com.exfarnanda1945.movietix.detail.repository.GetDetailFilmUseCaseImpl
import com.exfarnanda1945.movietix.detail.service.GetDetailFilmRemoteRepositoryImpl
import com.exfarnanda1945.movietix.detail.service.GetDetailFilmService
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val detailFilmModule = module {
    factory { HttpFactories.createRemoteService(get(), GetDetailFilmService::class.java) }
    factoryOf(::GetDetailFilmRemoteRepositoryImpl) { bind<GetDetailFilmRemoteRepository>() }
    factoryOf(::GetDetailFilmUseCaseImpl) { bind<GetDetailFilmUseCase>() }
    viewModelOf(::DetailFilmViewModel)
}