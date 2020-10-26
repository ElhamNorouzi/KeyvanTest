package com.app.keyvantest.ui.movieDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.keyvantest.R
import com.app.keyvantest.api.models.response.MovieListResult
import com.app.keyvantest.databinding.DetailMovieBinding
import com.app.keyvantest.utils.BaseFragment
import com.app.keyvantest.utils.Constants
import com.squareup.picasso.Picasso

class MovieDetails : BaseFragment() {

    private lateinit var binding: DetailMovieBinding
    private val movieResult: MovieListResult by lazy {
        MovieDetailsArgs.fromBundle(requireArguments()).data
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_movie,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(Constants.IMAGE_BASE_URL + movieResult.backdrop_path).into(binding.image)
        binding.rate.rating = movieResult.vote_average.toFloat()
        binding.playersCount.text = "vote counts: ${movieResult.vote_count}"
        binding.movieName.text = movieResult.title
        binding.description.text = movieResult.overview
        binding.genreName.text = movieResult.release_date
    }
}