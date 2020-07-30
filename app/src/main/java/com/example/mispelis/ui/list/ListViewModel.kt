package com.example.mispelis.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mispelis.model.remote.Movies
import com.example.mispelis.model.remote.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//1. Poner a heredar al ListViewModel de ViewModel() y pasarle los parametros
class ListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    //3.
    private val movies: MutableLiveData<List<Movies>> by lazy {
        MutableLiveData<List<Movies>>().also {
            loadMovies()
        }
    }

    fun getMovies(): LiveData<List<Movies>> {
        return movies
    }

    fun loadMovies() {
        GlobalScope.launch (Dispatchers.Main){
            movies.value = moviesRepository.getMovies().movies as List<Movies>


        }
    }
}

//2. Como el ViewModel tiene parametros se de crear con un factory
@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ListViewModel(moviesRepository) as T
}