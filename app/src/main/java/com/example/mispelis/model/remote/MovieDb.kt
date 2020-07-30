package com.example.mispelis.model.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDb {

    val urlAPI = "https://api.themoviedb.org/3/"


    val interceptor = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .client(interceptor)
        .build()
        .run {create<TheMovieDbService>(TheMovieDbService::class.java)}
}