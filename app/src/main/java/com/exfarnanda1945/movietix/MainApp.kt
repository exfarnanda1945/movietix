package com.exfarnanda1945.movietix

import android.app.Application
import com.exfarnanda1945.movietix.core.di.httpModule
import com.exfarnanda1945.movietix.home.banner.di.bannerModule
import com.exfarnanda1945.movietix.home.top_genre.di.topGenreModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            modules(
                httpModule,
                bannerModule,
                topGenreModule
            )
        }
    }
}