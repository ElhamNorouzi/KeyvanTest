package com.app.keyvantest.api.models.response

sealed class MoviesData
data class MoviesBean(
    val page: Int,
    val results: List<MovieListResult>,
    val total_pages: Int,
    val total_results: Int
) : MoviesData()

data class MoviesErr(val errors: List<String>) : MoviesData()