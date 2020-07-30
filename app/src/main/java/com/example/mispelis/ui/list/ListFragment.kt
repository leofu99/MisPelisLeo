package com.example.mispelis.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mispelis.R
import com.example.mispelis.model.remote.Movie
import com.example.mispelis.model.remote.Movies
import com.example.mispelis.model.remote.MoviesRepository
import com.example.mispelis.model.remote.TheMovieDbService
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Response

class ListFragment : Fragment(), MoviesAdapter.OnItemClickListener{

    private var listMovies = ArrayList<Movie>()
    private lateinit var listViewModel : ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel = ViewModelProvider(
            this,
            ListViewModelFactory(MoviesRepository())
        ).get()

        RV_list.setHasFixedSize(true)
        RV_list.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )

        listViewModel.getMovies().observe(viewLifecycleOwner, Observer{ movies ->
            val adapter = MoviesAdapter(movies as ArrayList<Movie>, this@ListFragment)
            RV_list.adapter = adapter
        })
    }

    override fun onItemClick(movie: Movie) {
        val action = ListFragmentDirections.actionNavigationListToDetailFragment(movie)
        findNavController().navigate(action)
    }


}