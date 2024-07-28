package com.exfarnanda1945.movietix.home.shared_discovery_film.di

import com.exfarnanda1945.movietix.core.di.HttpFactories
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.GetDiscoveryFilmUseCase
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.GetDiscoveryFilmRemoteRepository
import com.exfarnanda1945.movietix.home.shared_discovery_film.repository.GetDiscoveryFilmUseCaseImpl
import com.exfarnanda1945.movietix.home.shared_discovery_film.service.GetDiscoveryFilmRemoteRepositoryImpl
import com.exfarnanda1945.movietix.home.shared_discovery_film.service.GetDiscoveryFilmService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val discoveryFilmModule = module {
    factory { HttpFactories.createRemoteService(get(), GetDiscoveryFilmService::class.java) }
    factoryOf(::GetDiscoveryFilmRemoteRepositoryImpl) { bind<GetDiscoveryFilmRemoteRepository>() }
    factoryOf(::GetDiscoveryFilmUseCaseImpl) { bind<GetDiscoveryFilmUseCase>() }
}