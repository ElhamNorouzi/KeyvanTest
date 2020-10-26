package com.app.keyvantest.api

import com.app.keyvantest.api.models.response.MoviesBean
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

     //GetMovies
     @GET("3/discover/movie?api_key=59ef0aed8a8ebcd7164d2a7507b1303b&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false")
     fun getMoviesAsync(
         @Query("page") page: Int
     ): Deferred<MoviesBean>


    //Search in Movies
    @GET("/3/search/movie?api_key=59ef0aed8a8ebcd7164d2a7507b1303b&language=en-US&page=1&include_adult=false")
    fun searchInMoviesAsync(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Deferred<MoviesBean>
}