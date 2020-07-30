package com.example.mispelis.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mispelis.MisPelis
import com.example.mispelis.R
import com.example.mispelis.model.local.MovieFavorite
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), MoviesFavoritesAdapter.OnItemClickListener {

    private var listMovies = ArrayList<MovieFavorite>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RV_favorites.setHasFixedSize(true)
        RV_favorites.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )

        listMovies =
            MisPelis.database.MovieFavoritesDAO().loadFavoriteMovies() as ArrayList<MovieFavorite>

        val adapter = MoviesFavoritesAdapter(listMovies, this@FavoritesFragment)
        RV_favorites.adapter = adapter


    }

    override fun onItemClick(movie: MovieFavorite) {
        TODO("Not yet implemented")
    }
}