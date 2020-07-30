package com.example.mispelis

import android.app.Application
import androidx.room.Room
import com.example.mispelis.model.local.MovieFavoriteDataBase

class MisPelis : Application() {

    companion object {
        lateinit var database: MovieFavoriteDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            MovieFavoriteDataBase::class.java,
            "mismovies_db"
        ).allowMainThreadQueries()
            .build()
    }
}