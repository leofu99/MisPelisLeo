package com.example.mispelis.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mispelis.R
import com.example.mispelis.model.local.MovieFavorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesFavoritesAdapter(
    val moviesList: ArrayList<MovieFavorite>,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MoviesFavoritesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MoviesViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.setMovie(movie)
    }

    class MoviesViewHolder(
        itemView: View,
        val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        fun setMovie(movie: MovieFavorite) {
            val URL_IMAGES = "https://image.tmdb.org/t/p/w500"
            itemView.TV_title.text = movie.title
            itemView.TV_average.text = movie.vote_average.toString()
            Picasso.get().load(URL_IMAGES + movie.poster_path).into(itemView.IV_poster)
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(movie)
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(movie: MovieFavorite)
    }

}
