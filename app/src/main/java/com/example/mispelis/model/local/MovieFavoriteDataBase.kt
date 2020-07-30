package com.example.mispelis.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MovieFavorite::class),version = 1)

abstract class MovieFavoriteDataBase: RoomDatabase() {

    abstract fun MovieFavoritesDAO() : MovieFavoritesDAO

}