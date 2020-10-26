package com.app.keyvantest.ui.movieList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.keyvantest.R
import com.app.keyvantest.adapters.MoviesAdapter
import com.app.keyvantest.api.models.response.MoviesBean
import com.app.keyvantest.api.models.response.MoviesErr
import com.app.keyvantest.databinding.ListMovieBinding
import com.app.keyvantest.utils.BaseFragment
import java.util.*


class MovieList : BaseFragment() {
    private lateinit var binding: ListMovieBinding
    private lateinit var adapter: MoviesAdapter
    private val viewModelMovie: MovieListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieList()
        getMovies()
        initSearch()


    }

    private fun initMovieList() {
        adapter = MoviesAdapter(requireContext()) {
            findNavController().navigate(
                MovieListDirections.actionMoviesListToMovieDetails(it)
            )
        }
        val layoutManger = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.moviesListRecycler.layoutManager = layoutManger
        binding.moviesListRecycler.adapter = adapter
    }

    private fun getMovies() {
        initLoading(binding.loading, true)
        viewModelMovie.getMovies(1)
        viewModelMovie.getMoviesItems().observe(viewLifecycleOwner, {
            when (it) {
                is MoviesBean -> {
                    adapter.addItem(it.results)
                    initLoading(binding.loading, false)
                }
                is MoviesErr -> {
                    showLongToast("VPN" + it.errors.toString())
                    initLoading(binding.loading, false)
                }
            }
        })
    }

    private fun searchMovies(page: Int, query: String) {
        initLoading(binding.loading, true)
        viewModelMovie.searchMovies(page, query)
        viewModelMovie.getSearchMovies().observe(viewLifecycleOwner, {
            when (it) {
                is MoviesBean -> {
                    adapter.updateItem(it.results)
                    initLoading(binding.loading, false)
                }
                is MoviesErr -> {
                    showLongToast("VPN" + it.errors.toString())
                    initLoading(binding.loading, false)
                }
            }
        })
    }

    private fun initSearch() {
        binding.inputSearch.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                }

                private var timer: Timer = Timer()
                private val DELAY: Long = 1000 // milliseconds
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                requireActivity().runOnUiThread {
                                    if (binding.inputSearch.text.toString().isEmpty())
                                        getMovies()
                                    else
                                        searchMovies(
                                            1,
                                            binding.inputSearch.text.toString()
                                        )
                                }
                            }

                        },
                        DELAY
                    )
                }


            }
        )
    }
}