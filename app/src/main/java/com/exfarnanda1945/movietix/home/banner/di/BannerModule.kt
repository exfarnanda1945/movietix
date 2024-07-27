package com.exfarnanda1945.movietix.home.banner.di

import com.exfarnanda1945.movietix.core.di.HttpFactories
import com.exfarnanda1945.movietix.home.banner.domain.GetBannerUseCase
import com.exfarnanda1945.movietix.home.banner.presentation.BannerViewModel
import com.exfarnanda1945.movietix.home.banner.repository.GetBannerRemoteRepository
import com.exfarnanda1945.movietix.home.banner.repository.GetBannerUseCaseImpl
import com.exfarnanda1945.movietix.home.banner.service.GetBannerRemoteRepositoryImpl
import com.exfarnanda1945.movietix.home.banner.service.GetBannerService
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val bannerModule = module {
    factory { HttpFactories.createRemoteService(get(), GetBannerService::class.java) }
    factoryOf(::GetBannerRemoteRepositoryImpl) { bind<GetBannerRemoteRepository>() }
    factoryOf(::GetBannerUseCaseImpl) { bind<GetBannerUseCase>() }
    viewModelOf(::BannerViewModel)
}