package com.example.mispelis.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mispelis.MisPelis
import com.example.mispelis.R
import com.example.mispelis.model.local.MovieFavorite
import com.example.mispelis.model.remote.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            movie = safeArgs.Movie
            setData(movie)
        }

        val movieFavoriteDAO = MisPelis.database.MovieFavoritesDAO()

        val movieFav = movieFavoriteDAO.searchFavoriteMovie(movie.id)


        if (movieFav != null) {
            IV_favorite.setImageDrawable(resources.getDrawable(android.R.drawable.star_big_on))
        }


        IV_favorite.setOnClickListener {

            val movieFavorite = MovieFavorite(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                poster_path = movie.posterPath,
                vote_average = movie.voteAverage
            )

            if (movieFav == null) {
                movieFavoriteDAO.insertFavoriteMovie(movieFavorite)
                IV_favorite.setImageDrawable(resources.getDrawable(android.R.drawable.star_big_on))
            } else {
                movieFavoriteDAO.deleteFavoriteMovie(movieFavorite)
                IV_favorite.setImageDrawable(resources.getDrawable(android.R.drawable.star_big_off))
            }

        }
    }

    private fun setData(movie: Movie) {
        TV_detail_title.text = movie.title
        TV_budget.text = movie.voteAverage.toString()
        TV_overiew.text = movie.overview
        val URL_IMAGES = "https://image.tmdb.org/t/p/w500"
        Picasso.get().load(URL_IMAGES + movie.posterPath).into(IV_detail_poster)
    }

}