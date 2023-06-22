package com.example.mynewsapp.manualdi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsModule {

    private val okHttpClient: OkHttpClient by lazy { provideOkHttpClient() }
    val retrofit: Retrofit by lazy {
        provideRetrofit()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()// Patron de diseño BUILDER
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://newsapi.org/v2/")
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY


        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

}