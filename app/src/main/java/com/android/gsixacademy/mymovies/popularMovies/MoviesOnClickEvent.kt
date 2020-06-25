package com.android.gsixacademy.mymovies.popularMovies

import com.android.gsixacademy.mymovies.models.MovieResult

sealed class MoviesOnClickEvent {
data class onMovieClicked (var movie :MovieResult) : MoviesOnClickEvent()
}