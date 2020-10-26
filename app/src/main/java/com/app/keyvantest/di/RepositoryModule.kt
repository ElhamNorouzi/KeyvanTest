package com.app.keyvantest.di

import com.app.keyvantest.ui.movieDetails.MovieDetailsRepository
import com.app.keyvantest.ui.movieList.MovieListRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single { MovieListRepository() }
        single { MovieDetailsRepository() }
    }
}