package com.exfarnanda1945.movietix.home.indonesia.di

import com.exfarnanda1945.movietix.home.indonesia.presentation.IndonesiaFilmViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val indonesiaFilmModule = module {
    viewModelOf(::IndonesiaFilmViewModel)
}