package com.example.mispelis.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {
    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("api_key") apikey:String): Movies
}