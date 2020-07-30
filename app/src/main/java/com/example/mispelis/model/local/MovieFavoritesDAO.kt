package com.example.mispelis.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieFavoritesDAO {

    @Insert
    fun insertFavoriteMovie(movieFavorite: MovieFavorite)

    @Query("SELECT * FROM tabla_favoritas")
    fun loadFavoriteMovies() : List<MovieFavorite>

    @Query("SELECT * FROM tabla_favoritas WHERE id LIKE :id")
    fun searchFavoriteMovie(id: Int) : MovieFavorite

    @Delete
    fun deleteFavoriteMovie(movieFavorite: MovieFavorite)

}