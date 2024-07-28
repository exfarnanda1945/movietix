package com.exfarnanda1945.movietix.home.war_film.di

import com.exfarnanda1945.movietix.home.war_film.presentation.WarFilmViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val warFilmModule = module {
    viewModelOf(::WarFilmViewModel)
}