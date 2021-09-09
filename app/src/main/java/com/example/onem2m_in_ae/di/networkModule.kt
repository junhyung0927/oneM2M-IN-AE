package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.service.HeaderInterceptor
import com.example.onem2m_in_ae.service.OneM2MService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getNetworkModule(baseUrl: String) = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.BODY
                }
            })
            .build()
    }

    single {
        GsonConverterFactory
            .create(GsonBuilder().setLenient().create()) as Converter.Factory
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(get())
            .build()
            .create(OneM2MService::class.java)
    }
}
