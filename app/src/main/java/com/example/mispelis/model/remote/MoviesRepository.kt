package com.example.mispelis.model.remote

class MoviesRepository {

    val apiKey = "4ba206bfaa9a8ef00f1a4d3fd62af592"

    suspend fun getMovies() = MovieDb.retrofit.getTopRated(apiKey)

}