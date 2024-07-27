package com.exfarnanda1945.movietix.core.di

import com.exfarnanda1945.movietix.core.Constants.BASE_URL
import com.exfarnanda1945.movietix.core.Constants.TOKEN
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val httpModule = module {
    single { HttpFactories.createHttpLogger() }
    single { HttpFactories.createMoshiSerialize() }
    single { HttpFactories.createOkhttpClient(get()) }
    single { HttpFactories.createRetrofit(get(), get()) }
}


object HttpFactories {

    fun createHttpLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        return logger
    }

    fun createMoshiSerialize(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun createOkhttpClient(logger: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                request.addHeader("Authorization", "Bearer $TOKEN")
                chain.proceed(request.build())
            })
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).build()

    fun createRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    fun <T> createRemoteService(retrofit: Retrofit, service: Class<T>): T = retrofit.create(service)
}