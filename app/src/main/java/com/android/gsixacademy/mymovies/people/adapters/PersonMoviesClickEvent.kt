package com.android.gsixacademy.mymovies.people.adapters

sealed class PersonMoviesClickEvent {
    data class onPersonMovieClicked (val movieId : Int?) : PersonMoviesClickEvent()
}