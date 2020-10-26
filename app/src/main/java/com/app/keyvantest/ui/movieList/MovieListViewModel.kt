package com.app.keyvantest.ui.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.keyvantest.api.models.response.MoviesData
import org.koin.java.KoinJavaComponent.inject

class MovieListViewModel: ViewModel() {
        private val repository by inject(MovieListRepository::class.java)

    private var moviesItems = MutableLiveData<MoviesData>()
    fun getMoviesItems() : MutableLiveData<MoviesData> = moviesItems
    fun getMovies(page: Int){
        moviesItems = repository.getMovieList(page)
    }

    private var searchItems = MutableLiveData<MoviesData>()
    fun getSearchMovies(): MutableLiveData<MoviesData> = searchItems
    fun searchMovies(page: Int,query: String){
        searchItems = repository.searchMovies(page,query)
    }

}