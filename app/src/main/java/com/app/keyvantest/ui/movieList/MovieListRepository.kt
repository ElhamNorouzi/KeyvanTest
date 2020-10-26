package com.app.keyvantest.ui.movieList

import androidx.lifecycle.MutableLiveData
import com.app.keyvantest.api.ApiCall
import com.app.keyvantest.api.CallRetrofit
import com.app.keyvantest.api.ErrorResponse
import com.app.keyvantest.api.models.response.MoviesData
import com.app.keyvantest.api.models.response.MoviesErr
import org.koin.java.KoinJavaComponent.inject

class MovieListRepository {
    private val apiCall by inject(ApiCall::class.java)

    fun getMovieList(page: Int): MutableLiveData<MoviesData> {
        val data = MutableLiveData<MoviesData>()
        CallRetrofit.callApi(apiCall.getMoviesAsync(page),{
            data.value = it
        },{
            data.value = MoviesErr(listOf(it.msg))
        })
        return data
    }

    fun searchMovies(page: Int,query: String ): MutableLiveData<MoviesData> {
        val item = MutableLiveData<MoviesData>()
        CallRetrofit.callApi(apiCall.searchInMoviesAsync(page,query),{
            item.value = it
        },{
            item.value = MoviesErr(listOf(it.msg))
        })
        return item
    }
}